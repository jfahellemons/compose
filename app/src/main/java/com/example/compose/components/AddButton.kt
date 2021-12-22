package com.example.compose.components

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Chat
import androidx.compose.runtime.Composable

@Composable
fun AddButton(onOpenNewChat: () -> Unit) {
    Button(onClick = { onOpenNewChat() }) {
        Icon(Icons.Rounded.Chat, contentDescription = "new chat")
    }
}
