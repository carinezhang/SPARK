package com.socialnetwork

import java.time.Instant
import java.net.URI

case class Post(id: String, updatedOn: Instant, author: Int, text: String, image: URI, deleted: Boolean)

/*object Post {
  implicit val record: Record[Post] = new Record[Post] {
    val topic = "posts"
  }
}*/


