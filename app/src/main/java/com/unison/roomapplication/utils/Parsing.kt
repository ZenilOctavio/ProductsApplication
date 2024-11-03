package com.unison.roomapplication.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun stringToDouble(input: String): Double?{
    val doubleRegex = "^[+-]?([0-9]+\\.[0-9]*|[0-9]*\\.[0-9]+|[0-9]+)$".toRegex()
    if (input.matches(doubleRegex)) {
        return input.toDouble()
    } else {
        return null // Or throw an exception if you prefer
    }
}

