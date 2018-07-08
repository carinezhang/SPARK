package com.socialnetwork

import java.time.Instant
import java.net.URI

case class Post(id: String, author: String, text: String, image: String, deleted: Boolean)

object Post {
  implicit val record: Record[Post] = new Record[Post] {
    val topic = "posts"
  }
}


