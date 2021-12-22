package com.example.compose.repositories

import com.example.compose.data.Contact
import com.example.compose.database.ContactDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ContactsRepositoryImpl @Inject constructor(private val contactDao: ContactDao): ContactsRepository {
    override suspend fun getAll(): List<Contact> = withContext(Dispatchers.IO) {
        contactDao.getAll()
    }

    override suspend fun insert(contact: Contact) = withContext(Dispatchers.IO) {
        contactDao.insert(contact)
    }

    override suspend fun getContact(contactId: Int)= withContext(Dispatchers.IO) {
        contactDao.getContact(contactId)
    }
}
