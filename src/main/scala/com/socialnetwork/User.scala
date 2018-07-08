package com.socialnetwork

import java.time.Instant
import java.net.URI

case class User(id: String, image: String, nickname: String, verified: Boolean, deleted: Boolean)

object User {
  implicit val user: Record[User] = new Record[User] {
    val topic = "users"
    //def timestamp(user: User): Long = user.updatedOn.toEpochMilli
  }
}