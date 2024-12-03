package com.twitter.service.chat

import com.twitter.data.models.Message
import com.twitter.data.repository.chat.ChatRepository
import com.twitter.data.responses.ChatDto

class ChatService(
    private val chatRepository: ChatRepository
) {

    suspend fun doesChatBelongToUser(chatId: String, userId: String): Boolean {
        return chatRepository.doesChatBelongToUser(chatId, userId)
    }

    suspend fun getMessagesForChat(chatId: String, page: Int, pageSize: Int): List<Message> {
        return chatRepository.getMessagesForChat(chatId, page, pageSize)
    }

    suspend fun getChatsForUser(ownUserId: String): List<ChatDto> {
        return chatRepository.getChatsForUser(ownUserId)
    }
}