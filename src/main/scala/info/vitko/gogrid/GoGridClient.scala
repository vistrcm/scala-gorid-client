package info.vitko.gogrid

import java.security.MessageDigest

import dispatch.Defaults._
import dispatch._

import scalaz._, Scalaz._
import argonaut._, Argonaut._
/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 17/09/14
 * Time: 17:03
 */
class GoGridClient(key: String, secret: String,
                   apiUrl: String = "https://api.gogrid.com/api",
                   version: String = "1.9") {

  def this() = this(GoGridConfig.AccessConfig.key, GoGridConfig.AccessConfig.secret,
                    GoGridConfig.apiUrl, GoGridConfig.version)

  def listServers() = apiRequest("/grid/server/list")

  def listJobs() = {
    val decoded = Parse.decodeOption[JobsResponse](apiRequest("/grid/job/list"))
    decoded.get.jobs
  }

  private def apiRequest(path: String, format: String = "json") = {
    val requestUrl = s"${this.apiUrl}/grid/server/list"
    val svc = url(requestUrl) <<? Map("api_key" -> key) <<? Map("sig" -> getSig()) <<?
      Map("v" -> version) <<? Map("format" -> format)
//    println(svc.toRequest)
    val respF = Http(svc OK as.String)

    // add handler to shutdown NIO engine on complete
    respF onComplete { case _ => Http.shutdown() }

    respF()
  }

  private def getSig() = {
    val timestamp = System.currentTimeMillis / 1000
    val string = key + secret + timestamp
    val digest = MessageDigest.getInstance("MD5")
    digest.digest(string.getBytes).map("%02x".format(_)).mkString
  }

}

object GoGridClient extends App {
  val client = new GoGridClient()
//  println(client.listServers())
  val jbs = client.listJobs()
  for (job <- jbs) println(job)
}
