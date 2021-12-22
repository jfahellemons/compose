package com.example.compose.repositories

import com.example.compose.data.Message

interface MessageRepository {
    suspend fun insertMessage(message: Message)
}
