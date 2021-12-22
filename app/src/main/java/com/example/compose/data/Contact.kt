package com.example.compose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(@PrimaryKey val contactId: Int, val firstname: String, val surname: String, val number: Int, val imageUrl: String) {
    fun getFullname() = "$firstname $surname"
}
