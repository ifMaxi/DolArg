package com.maxidev.dolarg.utils

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
object DateTimeUtils {

    private val dateTime = LocalDateTime.now()
    private val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    val formattedDate: String = dateTime.format(pattern)
}