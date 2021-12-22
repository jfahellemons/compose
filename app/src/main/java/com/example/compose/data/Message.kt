package com.example.compose.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.*

@Entity
data class Message(
    @PrimaryKey(autoGenerate = true) val messageId: Int = 0,
    val chatId: Int,
    val content: String,
    val attachment: String? = null,
    val timeStamp: Date,
    var isRead: Boolean = false
)
