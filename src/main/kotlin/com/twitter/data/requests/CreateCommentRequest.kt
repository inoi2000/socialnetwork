package com.twitter.data.requests

data class CreateCommentRequest(
    val comment: String,
    val postId: String,
)
