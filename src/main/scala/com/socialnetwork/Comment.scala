package com.socialnetwork

import java.time.Instant
import java.net.URI

case class Comment(id: String, postId: Int, author: Int, text: String, deleted: Boolean)

/*object Comment {
  implicit val record: Record[Comment] = new Record[Comment] {
    val topic = "comments"
    def key(comment: Comment): Id[Comment] = comment.id
   // def timestamp(comment: Comment): Long = comment.updatedOn.toEpochMilli
  }
}*/