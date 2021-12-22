package com.example.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.compose.data.Chat
import java.util.*

@Composable
fun ChatItem(chat: Chat, onClickChat: (Int) -> Unit) {
    Row(
        Modifier
            .padding(5.dp)
            .background(Color.White)
            .clickable { onClickChat(chat.contactId) }) {
        ConstraintLayout() {
            val (image, text, divider) = createRefs()
            UserImage(imageUrl = chat.userPhoto, Modifier.constrainAs(image) {
                top.linkTo(parent.top, 8.dp)
                bottom.linkTo(parent.bottom, 8.dp)
                start.linkTo(parent.start, 8.dp)
            })
            Row(Modifier.constrainAs(text) {
                top.linkTo(image.top)
                bottom.linkTo(image.bottom)
                start.linkTo(image.end, 16.dp)
            }) {
                Column(Modifier.weight(2f)) {
                    Text(text = chat.userName, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    val lastMessage = chat.getLastMessage()
                    if (lastMessage != null) {
                        Text(text = lastMessage.content)
                    }
                }
                Column(Modifier.weight(1f)) {
                    Text(text = chat.getLastMessageTime(), fontSize = 12.sp)
                }
            }
            Divider(Modifier.height(0.5.dp).constrainAs(divider) {
                top.linkTo(text.bottom, 16.dp)
                end.linkTo(parent.end)
                start.linkTo(text.start, 16.dp)
            } )
        }
    }
}

@Composable
fun UserImage(imageUrl: String, modifier: Modifier) {
    Image(
        painter = rememberImagePainter(
            data = imageUrl,
            builder = {
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = "User image",
        modifier = modifier.size(50.dp),
    )
}
