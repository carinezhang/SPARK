package com.socialnetwork

case class DenormalisedPost(post: Post, author: User, interactions: Interactions)
case class Interactions(comments: Int)