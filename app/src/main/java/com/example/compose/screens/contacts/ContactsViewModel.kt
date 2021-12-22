package com.example.compose.screens.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.data.Contact
import com.example.compose.repositories.ContactsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(private val contactsRepository: ContactsRepository): ViewModel() {
    private var _contacts = MutableLiveData(listOf<Contact>())
    val contacts: LiveData<List<Contact>> = _contacts

    fun getContacts() {
        viewModelScope.launch {
            _contacts.postValue(contactsRepository.getAll())
        }
    }
}
