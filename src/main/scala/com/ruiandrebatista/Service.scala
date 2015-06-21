package com.ruiandrebatista

import akka.actor.ActorSystem
import slick.driver.PostgresDriver.simple._
import akka.stream.FlowMaterializer
import scala.concurrent.ExecutionContext
import com.typesafe.config.Config
import akka.event.{ Logging, LoggingAdapter }
import java.util.UUID


/**
 * @author ruiandrebatista
 */
trait Service {
  implicit val system: ActorSystem
  implicit val executionContext: ExecutionContext
  implicit val flowMaterializer: FlowMaterializer
  implicit val database: Database

  def config: Config
  def logger: LoggingAdapter

  def initializeDatabaseSchema() = {
    database.run(DataStore.createSchemaAction)
  }
  
  def getStoredFile(guid : UUID) = database.run(DataStore.getByGUIDAction(guid))
  
  
}