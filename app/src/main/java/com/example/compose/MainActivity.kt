package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.compose.screens.chatDetail.ContactChatScreen
import com.example.compose.screens.chatOverview.ChatScreen
import com.example.compose.screens.contacts.NewChat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsappStart()
        }
    }
}

private val LightColors = lightColors(
    primary = Color(0xff128C7E),
    primaryVariant = Color(0xff075E54)
)

@Composable
fun WhatsappStart() {
    val navController = rememberNavController()
    MaterialTheme(colors = LightColors) {
        NavHost(navController = navController, startDestination = "overView") {
            composable("newChat") {
                NewChat(
                    onClickContact = { navController.navigate("chatDetail/${it}") },
                    onNavigationBack = { navController.navigateUp() })
            }
            composable("overView") { ChatScreen(navController) }
            composable(
                "chatDetail/{contactId}",
                arguments = listOf(navArgument("contactId") { type = NavType.IntType })
            ) {
                ContactChatScreen(
                    onNavigationBack = { navController.navigateUp() },
                    contactId = it.arguments?.getInt("contactId")!!
                )
            }
        }
    }
}
