package com.example.compose.screens.chatDetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import com.example.compose.R
import com.example.compose.components.*
import com.example.compose.data.Chat
import com.example.compose.data.Contact
import com.example.compose.data.Message
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun ContactChatScreen(onNavigationBack: () -> Unit, contactId: Int) {
    val (text, setText) = remember { mutableStateOf("") }
    val chatDetailViewModel = hiltViewModel<ChatDetailViewModel>()
    LaunchedEffect(key1 = contactId) {
        chatDetailViewModel.getContact(contactId)
    }
    val contact: Contact? by chatDetailViewModel.contact.observeAsState()
    val messages = chatDetailViewModel.messages

    if (contact != null) {
        ChatDetailScreen(
            onNavigationBack = onNavigationBack,
            contact = contact!!,
            messages = messages,
            text,
            onImeAction = {
                chatDetailViewModel.createNewMessage(text)
                setText("")
                          },
            onTextChange = setText
        )
    }
}

@Composable
fun ChatDetailScreen(
    onNavigationBack: () -> Unit,
    contact: Contact,
    messages: List<Message>?,
    text: String,
    onImeAction: () -> Unit,
    onTextChange: (String) -> Unit
) {
    Scaffold(topBar = { ChatTopBar(contact = contact, onNavigationBack) }) {
        BackgroundContainer(backgroundDrawable = R.drawable.whatsapp_background, content = {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val (input, messageList) = createRefs()
                messages?.let {
                    MessagesList(modifier = Modifier.constrainAs(messageList) {
                        top.linkTo(parent.top, 16.dp)
                        start.linkTo(parent.start, 8.dp)
                        end.linkTo(parent.end, 8.dp)
                        bottom.linkTo(input.top, 16.dp)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }, messages = it)
                }
                Row(Modifier.constrainAs(input) {
                    bottom.linkTo(parent.bottom, 24.dp)
                    start.linkTo(parent.start, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }) {
                    ConstraintLayout() {
                        val (messageInput, button) = createRefs()
                        MessageInput(
                            onImeAction = onImeAction,
                            onTextChange = onTextChange,
                            text = text,
                            modifier = Modifier.constrainAs(messageInput) {}
                        )
                        if (text.isNotEmpty()) {
                            Button(onClick = onImeAction, modifier = Modifier.constrainAs(button) {
                                top.linkTo(messageInput.top)
                                bottom.linkTo(messageInput.bottom)
                                start.linkTo(messageInput.end, 8.dp)
                                end.linkTo(parent.end, 16.dp)
                            }) {
                                Text(text = "send")
                            }
                        }
                    }
                }
            }
        })
    }
}

@Composable
fun ChatTopBar(contact: Contact, onNavigationBack: () -> Unit) {
    HeaderComponent(navigationIcon = {
        IconButton(onClick = onNavigationBack) {
            Icon(Icons.Filled.ArrowBack, contentDescription = "back")
        }
    }, content = {
        Row {
            ContactItem(
                contact = contact,
                onClickContact = {},
                modifier = Modifier.padding(start = 0.dp, top = 16.dp, bottom = 16.dp)
            )
        }
    })
}

@Composable
fun MessagesList(modifier: Modifier = Modifier, messages: List<Message>) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyColumn(modifier = modifier, state = listState) {
        items(items = messages) { message ->
            MessageItem(message)
        }
    }

    LaunchedEffect(messages.size) {
        coroutineScope.launch {
            // Animate scroll to the first item
            if (messages.size > 1) {
                listState.scrollToItem(messages.size - 1)
            }
        }
    }
}

@Preview
@Composable
fun ChatDetailPreview() {
    val contact = Contact(1, "test", "user", 8579458, "")
    val chat = Chat(2, 1, "df", "")
    chat.messages = listOf(
        Message(chatId = 2, content = "Lorem ipsum qunet ipms", timeStamp = Date()),
        Message(chatId = 2, content = "Lorem ipsum qunet ipms", timeStamp = Date())
    )
    ChatDetailScreen(
        onNavigationBack = { /*TODO*/ },
        contact = contact,
        messages = null,
        "",
        {},
        {})
}
