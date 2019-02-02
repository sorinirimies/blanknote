package ro.sorin.blanknote.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.fromMillisToTimeString(): String {
    val format = SimpleDateFormat("hh:mm a", Locale.getDefault())
    return format.format(this)
}