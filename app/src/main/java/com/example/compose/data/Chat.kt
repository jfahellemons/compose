package com.example.compose.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.compose.utils.DateUtils

@Entity
data class Chat(
    @PrimaryKey(autoGenerate = true)
    val chatId: Int = 0,
    val contactId: Int,
    var userName: String,
    var userPhoto: String,
) {
    @Ignore
    var messages: List<Message>? = null
    fun getLastMessage(): Message? = if (messages?.isNotEmpty() == true) messages?.last() else null
    fun getUnreadCount(): Int = messages?.filter { !it.isRead }?.size ?: 0
    fun getLastMessageTime(): String = getLastMessage()?.let {
        DateUtils.toLastMessageTime(it.timeStamp, "h:MM")
    } ?: ""
}
