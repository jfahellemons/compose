package com.example.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose.data.Message
import com.example.compose.utils.DateUtils
import java.util.*

@Composable
fun MessageItem(message: Message) {
    Card(
        modifier = Modifier
            .padding(15.dp)
            .clickable { },
        elevation = 5.dp,
        shape = RoundedCornerShape(50.dp),
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 8.dp)
        ) {
            ConstraintLayout {
                val (text, time) = createRefs()
                Text(text = message.content, Modifier.constrainAs(text) {

                })
                Text(
                    text = DateUtils.toLastMessageTime(message.timeStamp, "h:MM"),
                    Modifier.constrainAs(time) {
                        top.linkTo(text.bottom, 2.dp)
                        end.linkTo(parent.end)
                    }, fontSize = 8.sp)
            }
        }
    }
}

@Preview
@Composable
fun PreviewMessage() {
    val message = Message(chatId = 1, content = "Hey hoe is het?", timeStamp = Date())
    MessageItem(message = message)
}
