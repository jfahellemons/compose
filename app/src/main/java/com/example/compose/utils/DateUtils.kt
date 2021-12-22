package com.example.compose.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun toLastMessageTime(date: Date, format: String) : String = SimpleDateFormat(format).format(date)
}
