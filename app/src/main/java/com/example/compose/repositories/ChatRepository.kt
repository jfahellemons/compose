package com.example.compose.repositories

import com.example.compose.data.Chat
import com.example.compose.data.dbRelations.ChatWithMessages

interface ChatRepository {
    suspend fun getChats(): List<Chat>
    suspend fun getChat(contactId: Int): ChatWithMessages?
    suspend fun insertChat(chat: Chat)
    suspend fun deleteChat(chatId: Int)
}
