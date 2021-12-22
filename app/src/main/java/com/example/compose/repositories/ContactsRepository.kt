package com.example.compose.repositories

import com.example.compose.data.Contact

interface ContactsRepository {
    suspend fun getAll(): List<Contact>
    suspend fun insert(contact: Contact)
    suspend fun getContact(contactId: Int): Contact?
}
