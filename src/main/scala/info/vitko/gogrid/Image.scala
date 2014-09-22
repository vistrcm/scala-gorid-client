package info.vitko.gogrid

import argonaut.Argonaut._
import argonaut.CodecJson

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 22/09/14
 * Time: 21:57
 */
case class Image(isActive: Boolean,
                 updatedTime: Int,
                 isPublic: Boolean,
                 name: String,
                 imageType: GeneralOption,
                 price: Int,
                 obj: String,
                 owner: ImageOwner,
                 datacenterlist: List[ImageDatacenter],
                 state: GeneralOption,
                 friendlyName: String,
                 gsitype: GeneralOption,
                 location: String,
                 createdTime: Option[Int],
                 minram: GeneralOption,
                 os: GeneralOption,
                 id: Int,
                 billingtokens: List[BillingToken],
                 architecture: GeneralOption)

object Image {
  implicit def ImageCodecJson: CodecJson[Image] =
    casecodec19(Image.apply, Image.unapply)("isActive", "updatedTime", "isPublic", "name", "type", "price",
      "object", "owner", "datacenterlist", "state", "friendlyName", "gsitype", "location", "createdTime", "minram",
      "os", "id", "billingtokens", "architecture")
}
case class ImageOwner(obj: String, id: Int, name: String)

object ImageOwner{
  implicit def ImageOwnerCodecJson: CodecJson[ImageOwner] =
    casecodec3(ImageOwner.apply, ImageOwner.unapply)("object", "id", "name")
}

case class ImageDatacenter(createdTime: Option[Int], datacenter: GeneralOption, state: GeneralOption,
                           obj: String, updatedTime: Int)

object ImageDatacenter {
  implicit def ImageDatacenterCodecJson: CodecJson[ImageDatacenter] =
    casecodec5(ImageDatacenter.apply, ImageDatacenter.unapply)("createdTime", "datacenter", "state", "object",
      "updatedTime")
}

case class BillingToken(price: Int, obj: String, id: Int, name: String)

object BillingToken{
  implicit def BillingTokenCodecJson: CodecJson[BillingToken] =
    casecodec4(BillingToken.apply, BillingToken.unapply)("price", "object", "id", "name")
}
