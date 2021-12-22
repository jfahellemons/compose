package com.example.compose.screens.profilePage

import androidx.compose.runtime.Composable
import com.example.compose.components.ScrollableAppBar
import com.example.compose.data.Contact

@Composable
fun ProfileScreen(contact: Contact, onNavigationBack: () -> Unit) {
    ScrollableAppBar(title = contact.getFullname(), backgroundImgSrc = contact.imageUrl)
}
