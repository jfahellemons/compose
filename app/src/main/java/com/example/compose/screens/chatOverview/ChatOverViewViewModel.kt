package com.example.compose.screens.chatOverview

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.data.Chat
import com.example.compose.repositories.ChatRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatOverViewViewModel @Inject constructor(private val chatRepository: ChatRepositoryImpl): ViewModel() {
    private var _chats = MutableLiveData(listOf<Chat>())
    val chats: LiveData<List<Chat>> = _chats

    fun getChats() {
        viewModelScope.launch {
            _chats.postValue(chatRepository.getChats())
        }
    }
}
