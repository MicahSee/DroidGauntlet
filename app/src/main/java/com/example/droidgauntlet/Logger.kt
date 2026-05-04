package com.example.droidgauntlet

import android.content.Context
import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Logger {
    private val fmt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.US)

    fun tap(context: Context, challenge: String, element: String, action: String, injected: Boolean) {
        val line = "${fmt.format(Date())} challenge=$challenge element=\"$element\" action=$action injected=$injected"
        Log.d("DroidGauntlet", line)
        try {
            val f = File(context.getExternalFilesDir(null), "gauntlet.log")
            f.appendText(line + "\n")
        } catch (_: Exception) {}
    }
}
