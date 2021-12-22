package com.example.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ScrollableAppBar(
    title: String,
    backgroundImgSrc: String,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable (() -> Unit)? = null,
) {
    Surface(elevation = 8.dp) {
        BackgroundContainer(backgroundSrc = backgroundImgSrc) {
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )
        Row(modifier = modifier.padding(start = 12.dp)) {
            if (navigationIcon != null) {
                navigationIcon()
            }
        }
    }
}

@Preview
@Composable
fun PreviewScrollableAppBar() {
    ScrollableAppBar(title = "test", backgroundImgSrc = "")
}
