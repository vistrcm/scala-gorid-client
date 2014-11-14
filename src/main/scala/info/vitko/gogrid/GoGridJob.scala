package info.vitko.gogrid

import scalaz._, Scalaz._
import argonaut._, Argonaut._

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 19/09/14
 * Time: 16:01
 */

case class GoGridJob(attempts: Int,
                     command: GeneralOption,
                     createdon: Int,
                     currentstate: GeneralOption,
                     datacenter: GeneralOption,
                     detail: JobDetail,
                     history: List[JobHistoryEntry],
                     id: Int,
                     lastupdatedon: Option[Int],
                     obj: String,
                     objecttype: GeneralOption,
                     owner: String) {

  override def toString = s"$createdon by $owner\t$command\t$detail"
}

case class JobDetail(
                      ram: Option[String],
                      description: Option[String],
                      name: String,
                      image: String,
                      detailType: String,
                      ip: Option[String]){

  override def toString() = s"${ip.getOrElse(None)}\t$name"
}

object JobDetail {
  implicit def JobDetailCodecJson: CodecJson[JobDetail] =
    casecodec6(JobDetail.apply, JobDetail.unapply)("ram", "description", "name", "image", "type", "ip")
}

case class JobHistoryEntry(id: Int, state: GeneralOption, updatedon: Option[Int])

object JobHistoryEntry {
  implicit def JobHistoryEntryCodecJson: CodecJson[JobHistoryEntry] =
    casecodec3(JobHistoryEntry.apply, JobHistoryEntry.unapply)("id", "state", "updatedon")
}

object GoGridJob {
  implicit def GoGridJobCodecJson: CodecJson[GoGridJob] =
    casecodec12(GoGridJob.apply, GoGridJob.unapply)("attempts", "command", "createdon", "currentstate",
      "datacenter", "detail", "history", "id", "lastupdatedon", "object", "objecttype", "owner")
}

case class JobsResponse(jobs: List[GoGridJob], method: String, status: String, summary: GoGridResponseSummary)

object JobsResponse {
  implicit def JobsResponseCodecJson: CodecJson[JobsResponse] =
    casecodec4(JobsResponse.apply, JobsResponse.unapply)("list", "method", "status", "summary")
}