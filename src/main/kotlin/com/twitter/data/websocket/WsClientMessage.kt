package com.twitter.data.websocket

import com.twitter.data.models.Message

data class WsClientMessage(
    val toId: String,
    val text: String,
    val chatId: String?,
) {
    fun toMessage(fromId: String): Message {
        return Message(
            fromId = fromId,
            toId = toId,
            text = text,
            timestamp = System.currentTimeMillis(),
            chatId = chatId
        )
    }
}
