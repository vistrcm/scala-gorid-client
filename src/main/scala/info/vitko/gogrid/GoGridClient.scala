package info.vitko.gogrid

import java.security.MessageDigest

import dispatch.Defaults._
import dispatch._

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 17/09/14
 * Time: 17:03
 */
class GoGridClient(key: String, secret: String,
                   apiUrl: String = "https://api.gogrid.com/api",
                   version: String = "1.9") {

  def this() = this(GoGridConfig.AccessConfig.key, GoGridConfig.AccessConfig.secret)

  def listServers() = {
    this.apiRequest("/grid/server/list")
  }

  private def apiRequest(path: String, format: String = "json") = {
    val requestUrl = s"${this.apiUrl}/grid/server/list"
    val svc = url(requestUrl) <<? Map("api_key" -> key) <<? Map("sig" -> getSig()) <<?
      Map("v" -> version) <<? Map("format" -> format)
    val resp = Http(svc OK as.String)
    resp()
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
  println(client.listServers())
}
