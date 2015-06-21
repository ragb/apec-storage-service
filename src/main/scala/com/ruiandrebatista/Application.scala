package com.ruiandrebatista

import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.RouteResult.route2HandlerFlow
import akka.stream.ActorFlowMaterializer
import slick.driver.PostgresDriver.api.Database

object Application extends App with Service with RestAPI {
  override val config = ConfigFactory.load()
  override implicit val system = ActorSystem("aplication")
  override implicit val executionContext = system.dispatcher
  override implicit val database = Database.forConfig("db.default")
  override implicit val flowMaterializer = ActorFlowMaterializer()

  override val logger = Logging(system, getClass)
  val httpInterface = config.getString("http.interface")
  val httpPort = config.getInt("http.port")
  
   val bindFuture = Http(system).bindAndHandle(routes, interface=httpInterface, port=httpPort, log=logger)
   
   val databaseInitializeFuture = initializeDatabaseSchema()

}