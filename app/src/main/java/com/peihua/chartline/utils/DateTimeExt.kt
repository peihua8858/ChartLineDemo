package com.peihua.chartline.utils


import android.annotation.SuppressLint
import com.peihua8858.tools.utils.toLong
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToLong

@SuppressLint("SimpleDateFormat")
private val format = SimpleDateFormat()
fun Date.format(pattern: String): String {
    format.applyPattern(pattern)
    return format.format(this)
}

fun Long.format(pattern: String): String {
    return Date(this).format(pattern)
}

fun Float.format(pattern: String): String {
    return Date(this.roundToLong()).format(pattern)
}
fun Any.format(pattern: String): String {
    return Date(this.toLong()).format(pattern)
}