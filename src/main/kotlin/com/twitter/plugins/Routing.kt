package com.twitter.plugins

import com.twitter.routes.*
import com.twitter.service.*
import com.twitter.service.chat.ChatController
import com.twitter.service.chat.ChatService
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val userService: UserService by inject()
    val followService: FollowService by inject()
    val postService: PostService by inject()
    val likeService: LikeService by inject()
    val commentService: CommentService by inject()
    val activityService: ActivityService by inject()
    val skillService: SkillService by inject()
    val chatService: ChatService by inject()
    val chatController: ChatController by inject()

    val jwtIssuer = environment.config.property("jwt.domain").getString()
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()
    routing {
        // User routes
        authenticate()
        createUser(userService)
        loginUser(
            userService = userService,
            jwtIssuer = jwtIssuer,
            jwtAudience = jwtAudience,
            jwtSecret = jwtSecret
        )
        searchUser(userService)
        getUserProfile(userService)
        getPostsForProfile(postService)
        updateUserProfile(userService)

        // Following routes
        followUser(followService, activityService)
        unfollowUser(followService)

        // Post routes
        createPost(postService)
        getPostsForFollows(postService)
        deletePost(postService, likeService, commentService)
        getPostDetails(postService)

        // Like routes
        likeParent(likeService, activityService)
        unlikeParent(likeService)
        getLikesForParent(likeService)

        // Comment routes
        createComment(commentService, activityService)
        deleteComment(commentService, likeService)
        getCommentsForPost(commentService)

        // Activity routes
        getActivities(activityService)

        // Skill routes
        getSkills(skillService)

        // Chat routes
        getChatsForUser(chatService)
        getMessagesForChat(chatService)
        chatWebSocket(chatController)

        static {
            resources("static")
        }
    }
}
