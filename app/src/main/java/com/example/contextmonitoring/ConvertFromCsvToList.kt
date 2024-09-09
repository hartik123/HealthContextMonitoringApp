package com.example.contextmonitoring

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

fun readCSVFile(context: Context, fileName: String): MutableList<Float> {
    val dataList = mutableListOf<Float>()
    try {
        val inputStream = context.assets.open(fileName)
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.useLines { lines ->
            lines.forEach { line ->
                val value = line.toFloatOrNull()
                value?.let { dataList.add(it) }
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return dataList
}
