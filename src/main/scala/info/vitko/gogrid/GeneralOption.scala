package info.vitko.gogrid

import argonaut.Argonaut._
import argonaut.CodecJson

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 22/09/14
 * Time: 22:41
 */
case class GeneralOption(obj: String, id: Int, name: String, description: Option[String]){
  override def toString = name
}

object GeneralOption{
  implicit def GeneralOptionCodecJson: CodecJson[GeneralOption] =
    casecodec4(GeneralOption.apply, GeneralOption.unapply)("object","id","name","description")
}
