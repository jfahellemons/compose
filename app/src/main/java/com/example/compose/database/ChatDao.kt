package com.example.compose.database

import androidx.room.*
import com.example.compose.data.Chat
import com.example.compose.data.dbRelations.ChatWithMessages

@Dao
interface ChatDao {
    @Query("SELECT * FROM Chat")
    fun getAll(): List<Chat>

    @Transaction
    @Query("SELECT * FROM CHAT WHERE contactId == :contactId")
    fun getChat(contactId: Int): ChatWithMessages

    @Insert
    fun insertAll(vararg chats: Chat)

    @Transaction
    @Query("SELECT * FROM Chat")
    fun chatsWithMessages(): List<ChatWithMessages>

    @Query("DELETE FROM Chat WHERE chatId == :chatId")
    fun delete(chatId: Int)
}
