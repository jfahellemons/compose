package com.example.compose.screens.chatOverview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.example.compose.components.AddButton
import com.example.compose.components.HeaderComponent
import com.example.compose.data.Chat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.components.BackgroundContainer
import com.example.compose.components.ChatItem

@Composable
fun ChatScreen(navController: NavController) {
    val chatOverViewViewModel = hiltViewModel<ChatOverViewViewModel>()
    chatOverViewViewModel.getChats()
    val chats: List<Chat> by chatOverViewViewModel.chats.observeAsState(listOf())
    Scaffold(topBar = { HeaderComponent(content = { Text(text = "Whatsapp")}, navigationIcon = {}) }, floatingActionButton = { AddButton(onOpenNewChat = {navController.navigate("newChat")}) }) { innerPadding ->
        ChatList(chats = chats, onClickChat = {navController.navigate("chatDetail/$it")})
    }
}

@Composable
fun ChatsOverlay(chats: List<Chat>, onClickChat: (Int) -> Unit) {
    BackgroundContainer(content = { ChatList(chats = chats, onClickChat) })
}

@Composable
fun ChatList(chats: List<Chat>, onClickChat: (Int) -> Unit) {
    if (chats.isNullOrEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "No chat history yet")
        }
    } else {
        LazyColumn {
            items(items = chats) { chat ->
                ChatItem(chat, onClickChat = onClickChat)
            }
        }
    }
}

@Preview
@Composable
fun ChatsOverviewPreview() {
    val chats = listOf<Chat>()
    MaterialTheme() {
        Scaffold(topBar = { HeaderComponent({}, {Text(text = "Whatsapp")}) }) { innerPadding ->
            ChatsOverlay(chats = chats, {})
        }
    }
}

