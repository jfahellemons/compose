package com.example.compose.repositories

import com.example.compose.data.Chat
import com.example.compose.data.Message
import com.example.compose.database.ChatDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepositoryImpl @Inject constructor(private val chatDao: ChatDao): ChatRepository {
    override suspend fun getChats() = withContext(Dispatchers.IO) {
            return@withContext chatDao.chatsWithMessages().map {
                    chatWithMessages ->
                chatWithMessages.chat.messages = chatWithMessages.messages
                chatWithMessages.chat
            }
    }

    override suspend fun getChat(contactId: Int) = withContext(Dispatchers.IO) {
        return@withContext chatDao.getChat(contactId)
    }

    override suspend fun insertChat(chat: Chat) = withContext(Dispatchers.IO) {
        chatDao.insertAll(chat)
    }

    override suspend fun deleteChat(chatId: Int) = chatDao.delete(chatId)
}
