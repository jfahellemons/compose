package com.example.compose.data.dbRelations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.compose.data.Chat
import com.example.compose.data.Message

data class ChatWithMessages (
    @Embedded val chat: Chat,
    @Relation(
        parentColumn = "chatId",
        entityColumn = "chatId"
    )
    val messages: List<Message>
)
