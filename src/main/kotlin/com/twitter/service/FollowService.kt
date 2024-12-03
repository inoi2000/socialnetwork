package com.twitter.service

import com.twitter.data.repository.follow.FollowRepository
import com.twitter.data.requests.FollowUpdateRequest

class FollowService(
    private val followRepository: FollowRepository
) {

    suspend fun followUserIfExists(request: FollowUpdateRequest, followingUserId: String): Boolean {
        return followRepository.followUserIfExists(
            followingUserId,
            request.followedUserId
        )
    }

    suspend fun unfollowUserIfExists(followedUserId: String, followingUserId: String): Boolean {
        return followRepository.unfollowUserIfExists(
            followingUserId,
            followedUserId
        )
    }
}