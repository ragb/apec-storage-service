package com.ruiandrebatista

import java.util.UUID

import slick.driver.PostgresDriver.api._

case class StoredFile(guid : Option[UUID] = None, name : String, contentType : String, storageType : String, storageUrl : String, disabled : Boolean)


class StoredFilesTable(tag : Tag) extends Table[StoredFile](tag, "STORED_FILES") {
  def guid = column[UUID]("guid", O.PrimaryKey)
  def name = column[String]("NAME")
  def contentType = column[String]("CONTENT_TYPE")
  def storageType = column[String]("STORAGE_TYPE")
  def storageUrl = column[String]("STORAGE_URL")
  def disabled = column[Boolean]("disabled", O.Default(false))
  def * = (guid.?, name, contentType, storageType, storageUrl, disabled) <> (StoredFile.tupled, StoredFile.unapply _)
  
}



 object DataStore {
  val storedFiles = TableQuery[StoredFilesTable]
  val schema = storedFiles.schema
  def createSchemaAction = DBIO.seq(schema.create)
  
    def getByGUIDAction(guid : UUID) = storedFiles.filter(_.guid === guid).result.headOption

    val insertQuery = storedFiles returning storedFiles.map(_.guid) into {(storedFile, guid) => storedFile.copy(guid=Some(guid))}

  def insertStoredFileAction(storedFile : StoredFile) = insertQuery += storedFile
  
  
 }