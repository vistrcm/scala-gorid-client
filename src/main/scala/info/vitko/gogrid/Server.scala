package info.vitko.gogrid

import argonaut.Argonaut._
import argonaut.CodecJson

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 22/09/14
 * Time: 21:42
 */

case class Server(datacenter: GeneralOption,
                  ram: GeneralOption,
                  description: Option[String],
                  srvType: GeneralOption,
                  image: Image,
                  obj: String,
                  state: GeneralOption,
                  isSandbox: Boolean,
                  ip: ServerIP,
                  cores: Option[Int],
                  diskSize: Option[Int],
                  os: GeneralOption,
                  id: Option[Int],
                  name: String) {

  override def toString = s"$id\t$name\t$ip\t($image)"
}

object Server {
  implicit def ServerCodecJson: CodecJson[Server] =
    casecodec14(Server.apply, Server.unapply)("datacenter", "ram", "description", "type", "image", "object", "state",
      "isSandbox", "ip", "cores", "diskSize", "os", "id", "name")
}

case class ServersResponse(servers: List[Server], method: String, status: String, summary: GoGridResponseSummary)

object ServersResponse {
  implicit def ServersResponseCodecJson: CodecJson[ServersResponse] =
    casecodec4(ServersResponse.apply, ServersResponse.unapply)("list", "method", "status", "summary")
}

case class ServerIP(subnet: String,
                    datacenter: GeneralOption,
                    ip: String,
                    obj: String,
                    public: Boolean,
                    state: GeneralOption,
                    id: Int) {

  override def toString() = ip
}

object ServerIP {
  implicit def ServerIPCodecJson: CodecJson[ServerIP] =
    casecodec7(ServerIP.apply, ServerIP.unapply)("subnet", "datacenter", "ip", "object", "public", "state","id")
}