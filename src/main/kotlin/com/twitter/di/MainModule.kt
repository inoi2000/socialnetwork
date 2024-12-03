package com.twitter.di

import com.google.gson.Gson
import com.twitter.data.repository.activity.ActivityRepository
import com.twitter.data.repository.activity.ActivityRepositoryImpl
import com.twitter.data.repository.chat.ChatRepository
import com.twitter.data.repository.chat.ChatRepositoryImpl
import com.twitter.data.repository.comment.CommentRepository
import com.twitter.data.repository.comment.CommentRepositoryImpl
import com.twitter.data.repository.follow.FollowRepository
import com.twitter.data.repository.follow.FollowRepositoryImpl
import com.twitter.data.repository.likes.LikeRepository
import com.twitter.data.repository.likes.LikeRepositoryImpl
import com.twitter.data.repository.post.PostRepository
import com.twitter.data.repository.post.PostRepositoryImpl
import com.twitter.data.repository.skill.SkillRepository
import com.twitter.data.repository.skill.SkillRepositoryImpl
import com.twitter.data.repository.user.UserRepository
import com.twitter.data.repository.user.UserRepositoryImpl
import com.twitter.service.*
import com.twitter.service.chat.ChatController
import com.twitter.service.chat.ChatService
import com.twitter.util.Constants
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val mainModule = module {
    single {
        val client = KMongo.createClient().coroutine
        client.getDatabase(Constants.DATABASE_NAME)
    }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<FollowRepository> {
        FollowRepositoryImpl(get())
    }
    single<PostRepository> {
        PostRepositoryImpl(get())
    }
    single<LikeRepository> {
        LikeRepositoryImpl(get())
    }
    single<CommentRepository> {
        CommentRepositoryImpl(get())
    }
    single<ActivityRepository> {
        ActivityRepositoryImpl(get())
    }
    single<SkillRepository> {
        SkillRepositoryImpl(get())
    }
    single<ChatRepository> {
        ChatRepositoryImpl(get())
    }
    single { UserService(get(), get()) }
    single { FollowService(get()) }
    single { PostService(get()) }
    single { LikeService(get(), get(), get()) }
    single { CommentService(get(), get()) }
    single { ActivityService(get(), get(), get()) }
    single { SkillService(get()) }
    single { ChatService(get()) }

    single { Gson() }

    single { ChatController(get()) }
}