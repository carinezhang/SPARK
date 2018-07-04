package com.socialnetwork

import java.time.Instant
import java.net.URI

case class Comment(id: Id[Comment], postId: Id[Post], updatedOn: Instant, author: Id[User], text: String, deleted: Boolean)

object Comment {
  implicit val record: Record[Comment] = new Record[Comment] {
    val topic = "comments"
    def key(comment: Comment): Id[Comment] = comment.id
    def timestamp(comment: Comment): Long = comment.updatedOn.toEpochMilli
  }
}