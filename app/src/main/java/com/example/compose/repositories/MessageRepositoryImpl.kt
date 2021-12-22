package com.example.compose.repositories

import com.example.compose.data.Message
import com.example.compose.database.MessageDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MessageRepositoryImpl @Inject constructor(private val messageDao: MessageDao): MessageRepository {
    override suspend fun insertMessage(message: Message) = withContext(Dispatchers.IO) {
        messageDao.insertAll(message)
    }
}
