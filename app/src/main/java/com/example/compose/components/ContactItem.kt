package com.example.compose.components

import android.view.Gravity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.compose.data.Contact

@Composable
fun ContactItem(contact: Contact, onClickContact: (Int) -> Unit, modifier: Modifier = Modifier) {
    Column(Modifier.clickable { onClickContact(contact.contactId) }) {
        Row(modifier = modifier) {
            ConstraintLayout() {
                val (image, text) = createRefs()
                UserImage(imageUrl = contact.imageUrl, modifier = Modifier.constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                })
                Text(text = contact.getFullname(), fontWeight = FontWeight.Bold, fontSize = 16.sp, modifier = Modifier.constrainAs(text) {
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.end, margin = 16.dp)
                })
            }
        }
        Divider(color = Color.Gray, thickness = 0.5.dp)
    }
}

@Preview()
@Composable()
fun ContactItemPreview() {
    val contact = Contact(1, "Test", "User", 638058095, "https://picsum.photos/300/300")
    ContactItem(contact = contact, {})
}
