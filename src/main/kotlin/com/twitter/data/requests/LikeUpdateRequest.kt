package com.twitter.data.requests

data class LikeUpdateRequest(
    val parentId: String,
    val parentType: Int
)
