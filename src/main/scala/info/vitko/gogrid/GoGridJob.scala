package info.vitko.gogrid

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 19/09/14
 * Time: 16:01
 */

import scalaz._, Scalaz._
import argonaut._, Argonaut._

case class JobCommand(description: String, id: Int, name: String, obj: String)  {
  override def toString = name
}

object JobCommand {
  // Define codecs easily from case classes
  implicit def JobCommandCodecJson: CodecJson[JobCommand] =
    casecodec4(JobCommand.apply, JobCommand.unapply)("description", "id", "name", "object")
}

case class JobCurrentstate(description: String, id: Int, name: String, obj: String)

object JobCurrentstate {
  implicit def JobCurrentstateCodecJson: CodecJson[JobCurrentstate] =
    casecodec4(JobCurrentstate.apply, JobCurrentstate.unapply)("description", "id", "name", "object")
}

case class JobDatacenter(description: String, id: Int, name: String, obj: String)

object JobDatacenter {
  implicit def JobDatacenterCodecJson: CodecJson[JobDatacenter] =
    casecodec4(JobDatacenter.apply, JobDatacenter.unapply)("description", "id", "name", "object")
}

case class JobDetail(description: Option[String], image: String, ip: String, name: String, detailType: String){
  override def toString() = s"$ip\t$name"
}

object JobDetail {
  implicit def JobDetailCodecJson: CodecJson[JobDetail] =
    casecodec5(JobDetail.apply, JobDetail.unapply)("description", "image", "ip", "name", "type")
}

case class JobHistoryEntryState(description: String, id: Int, name: String, obj: String)

object JobHistoryEntryState {
  implicit def JobHistoryEntryStateCodecJson: CodecJson[JobHistoryEntryState] =
    casecodec4(JobHistoryEntryState.apply, JobHistoryEntryState.unapply)("description", "id", "name", "object")
}

case class JobHistoryEntry(id: Int, state: JobHistoryEntryState, updatedon: Option[Int])

object JobHistoryEntry {
  implicit def JobHistoryEntryCodecJson: CodecJson[JobHistoryEntry] =
    casecodec3(JobHistoryEntry.apply, JobHistoryEntry.unapply)("id", "state", "updatedon")
}

case class JobObjecttype(description: Option[String], id: Int, name: String, obj: String)

object JobObjecttype {
  implicit def JobObjecttypeCodecJson: CodecJson[JobObjecttype] =
    casecodec4(JobObjecttype.apply, JobObjecttype.unapply)("description", "id", "name", "object")
}

case class GoGridJob(attempts: Int,
                     command: JobCommand,
                     createdon: Int,
                     currentstate: JobCurrentstate,
                     datacenter: JobDatacenter,
                     detail: JobDetail,
                     history: List[JobHistoryEntry],
                     id: Int,
                     lastupdatedon: Option[Int],
                     obj: String,
                     objecttype: JobObjecttype,
                     owner: String) {

  override def toString = s"$createdon by $owner\t$command\t$detail"
}

object GoGridJob {
  implicit def GoGridJobCodecJson: CodecJson[GoGridJob] =
    casecodec12(GoGridJob.apply, GoGridJob.unapply)("attempts", "command", "createdon", "currentstate",
      "datacenter", "detail", "history", "id", "lastupdatedon", "object", "objecttype", "owner")
}

case class JobsResponseSummary(numpages: Int, start: Int, total: Int, returned: Int)

object JobsResponseSummary {
  implicit def JobsResponseCodecJson: CodecJson[JobsResponseSummary] =
  casecodec4(JobsResponseSummary.apply, JobsResponseSummary.unapply)("numpages", "start", "total", "returned")
}

case class JobsResponse(jobs: List[GoGridJob], method: String, status: String, summary: JobsResponseSummary)

object JobsResponse {
  implicit def JobsResponseCodecJson: CodecJson[JobsResponse] =
    casecodec4(JobsResponse.apply, JobsResponse.unapply)("list", "method", "status", "summary")
}
