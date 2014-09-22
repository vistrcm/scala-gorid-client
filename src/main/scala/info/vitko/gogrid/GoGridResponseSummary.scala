package info.vitko.gogrid

import argonaut.Argonaut._
import argonaut.CodecJson

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 22/09/14
 * Time: 21:14
 */
case class GoGridResponseSummary(numpages: Int, start: Int, total: Int, returned: Int)

object GoGridResponseSummary {
  implicit def GoGridResponseSummaryCodecJson: CodecJson[GoGridResponseSummary] =
    casecodec4(GoGridResponseSummary.apply, GoGridResponseSummary.unapply)("numpages", "start", "total", "returned")
}
