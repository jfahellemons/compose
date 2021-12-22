package com.example.compose.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.compose.data.Contact

@Dao
interface ContactDao {
    @Query("SELECT * FROM Contact")
    fun getAll(): List<Contact>

    @Insert
    fun insert(contact: Contact)

    @Query("SELECT * FROM Contact WHERE contactId == :contactId")
    fun getContact(contactId: Int): Contact?
}
