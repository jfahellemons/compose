package com.example.compose.screens.contacts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.components.ContactItem
import com.example.compose.components.HeaderComponent
import com.example.compose.data.Contact

@Composable
fun NewChat(onClickContact: (Int) -> Unit, onNavigationBack: () -> Unit) {
    val contactsViewModel = hiltViewModel<ContactsViewModel>()
    contactsViewModel.getContacts()
    val contacts: List<Contact> by contactsViewModel.contacts.observeAsState(listOf())
    Scaffold(topBar = {  HeaderComponent(content = { Text(text = "New chat") }, navigationIcon = {
        IconButton(onClick = onNavigationBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "back")
        }
    }) }) {
        ContactsList(contacts = contacts, onClickContact)
    }
}

@Composable
fun ContactsList(contacts: List<Contact>, onClickContact: (Int) -> Unit) {
    LazyColumn {
        items(items = contacts) { contact ->
            ContactItem(contact = contact, onClickContact, modifier = Modifier.padding(16.dp))
        }
    }
}
