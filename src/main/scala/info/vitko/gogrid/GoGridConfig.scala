package info.vitko.gogrid

import com.typesafe.config.ConfigFactory

/**
 * Created with IntelliJ IDEA.
 * User: vist
 * Date: 18/09/14
 * Time: 16:25
 */
object GoGridConfig {
  private val config = ConfigFactory.load()

  lazy val apiUrl = config.getString("apiUrl")
  lazy val version = config.getString("version")

  object AccessConfig {
    private val accessConfig = config.getConfig("access")

    lazy val key = accessConfig.getString("key")
    lazy val secret = accessConfig.getString("secret")
  }
}