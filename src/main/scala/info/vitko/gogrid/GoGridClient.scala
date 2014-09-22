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
                   apiUrl: String = GoGridConfig.apiUrl,
                   version: String = GoGridConfig.version) {

  def this() = this(GoGridConfig.AccessConfig.key, GoGridConfig.AccessConfig.secret)

  def listServers() = {
    val response = Parse.decodeOption[ServersResponse](apiRequest("grid/server/list"))
    response.get.servers
  }

  def listJobs() = {
    val decoded = Parse.decodeOption[JobsResponse](apiRequest("grid/job/list"))
    decoded.get.jobs
  }

  private def apiRequest(path: String, format: String = "json") = {
    val requestUrl = s"${this.apiUrl}/${path}"
    val svc = url(requestUrl) <<? Map("api_key" -> key) <<? Map("sig" -> getSig()) <<?
      Map("v" -> version) <<? Map("format" -> format)
//    println(svc.toRequest)
    val respF = Http(svc OK as.String)

    // add handler to shutdown NIO engine on complete
//    respF onComplete { case _ => Http.shutdown() }

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
  // create GoGridClient
  val client = new GoGridClient()

  // list of jobs
  val jbs = client.listJobs()
  for (job <- jbs) println(job)

  // list of servers
  for (srv <- client.listServers()) println(srv)

  Http.shutdown()
}
