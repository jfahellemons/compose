package com.example.compose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.compose.data.Message

@Dao
interface MessageDao {
    @Query("SELECT * FROM message")
    fun getAll(): List<Message>

    @Query("SELECT * FROM message WHERE chatId == :chatId")
    fun findBychat(chatId: Int): List<Message>

    @Insert
    fun insertAll(vararg users: Message)
}
