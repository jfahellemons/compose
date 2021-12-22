package com.example.compose.screens.chatDetail

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.data.Chat
import com.example.compose.data.Contact
import com.example.compose.data.Message
import com.example.compose.repositories.ChatRepository
import com.example.compose.repositories.ContactsRepository
import com.example.compose.repositories.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(private val chatRepository: ChatRepository, private val contactsRepository: ContactsRepository, private val messageRepository: MessageRepository): ViewModel() {
    private var _chat: Chat? = null
    private var _contact = MutableLiveData<Contact>()
    val contact: LiveData<Contact> = _contact

    // state: todoItems
    var messages = mutableStateListOf<Message>()
        private set

    fun getContact(contactId: Int) {
        viewModelScope.launch {
            val contact = contactsRepository.getContact(contactId)
            _contact.postValue(contact)
            if (contact != null) {
                getChat(contactId)
            }
        }
    }

    fun getChat(contactId: Int) {
        viewModelScope.launch {
            val chatWithMessages = chatRepository.getChat(contactId)
            if (chatWithMessages != null) {
                messages.addAll(chatWithMessages.messages)
                _chat = chatWithMessages.chat
            }
        }
    }

    suspend fun createNewChat(contact: Contact) {
        val chat = Chat(userName = contact.getFullname(), contactId = contact.contactId, userPhoto = contact.imageUrl)
        chatRepository.insertChat(chat)
        _chat = chatRepository.getChat(contact.contactId)?.chat
    }

    fun createNewMessage(message: String) {
        viewModelScope.launch {
            if (message.isNotEmpty()) {
                if (_chat == null) {
                    createNewChat(contact.value!!)
                }
                _chat?.chatId?.let {
                    sendMessage(it, message)
                }
            }
        }
    }

    suspend fun sendMessage(chatId: Int, message: String) {
        val newMessage = Message(chatId = chatId, content = message, timeStamp = Date())
        messageRepository.insertMessage(newMessage)
        messages.add(newMessage)
    }
}
