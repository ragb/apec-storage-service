package com.ruiandrebatista

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json._
import akka.http.scaladsl.marshalling._
import java.util.UUID





case class Message(message : String)

trait JsonProtocol extends DefaultJsonProtocol {
  implicit object UUIDFormat extends JsonFormat[UUID] {
    def write(value : UUID) = JsString(value toString)
    
    def read(value : JsValue)  = value match {
      case JsString(x) => UUID.fromString(x)
      case _ => deserializationError("expecting UUID")
    }  }
    implicit val messageFormat = jsonFormat1(Message.apply)
    implicit val storedFileFormat = jsonFormat6(StoredFile.apply)
}

trait RestAPI  extends JsonProtocol with PredefinedToResponseMarshallers with PredefinedToEntityMarshallers {
self : Service =>
  
  def routes = 
    path("") {
      get {
        complete(StatusCodes.Created -> Message("ok"))
      }
    } ~path("file" / JavaUUID) {guid =>
      get {
        complete {
          getStoredFile(guid)
        }
      }
      }
}