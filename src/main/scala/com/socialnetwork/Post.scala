package com.socialnetwork

import java.time.Instant
import java.net.URI

case class Post(id: Id[Post], updatedOn: Instant, author: Id[User], text: String, image: URI, deleted: Boolean)

object Post {
  implicit val record: Record[Post] = new Record[Post] {
    val topic = "posts"
    def key(post: Post): Id[Post] = post.id
    def timestamp(post: Post): Long = post.updatedOn.toEpochMilli
  }
}


