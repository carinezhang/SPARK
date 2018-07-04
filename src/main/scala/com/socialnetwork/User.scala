package com.socialnetwork

import java.time.Instant
import java.net.URI

case class User(id: Id[User], updatedOn: Instant, image: URI, nickname: String, verified: Boolean, deleted: Boolean)


object User {
  implicit val user: Record[User] = new Record[User] {
    val topic = "users"
    def key(user: User): Id[User] = user.id
    def timestamp(user: User): Long = user.updatedOn.toEpochMilli
  }
}