package com.socialnetwork

import java.time.Instant
import java.net.URI

case class Comment(id: String, postId: String, author: String, text: String, deleted: Boolean)

object Comment {
  implicit val record: Record[Comment] = new Record[Comment] {
    val topic = "comments"
  }
}