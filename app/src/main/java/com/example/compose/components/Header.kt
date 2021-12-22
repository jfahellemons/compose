package com.example.compose.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun HeaderComponent(navigationIcon: @Composable () -> Unit, content: @Composable () -> Unit) {
    TopAppBar(title = content, navigationIcon = navigationIcon)
}
