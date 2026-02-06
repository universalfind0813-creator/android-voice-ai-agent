package com.example.voiceai

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.telephony.SmsManager
import androidx.annotation.RequiresApi

class TaskExecutor(private val context: Context) {

    fun makeCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        context.startActivity(intent)
    }

    fun sendSMS(phoneNumber: String, message: String) {
        try {
            val smsManager = SmsManager.getDefault()
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun openApp(packageName: String) {
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        if (intent != null) {
            context.startActivity(intent)
        }
    }

    fun openBrowser(query: String) {
        val url = "https://www.google.com/search?q=${Uri.encode(query)}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    fun setAlarm(timeInMillis: Long) {
        val intent = Intent(Intent.ACTION_SET_ALARM).apply {
            putExtra("android.intent.extra.alarm.HOUR", 10)
            putExtra("android.intent.extra.alarm.MINUTES", 30)
        }
        context.startActivity(intent)
    }

    fun openGoogleMaps(location: String) {
        val uri = Uri.parse("geo:0,0?q=${Uri.encode(location)}")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

    fun playMusic(artist: String? = null) {
        val intent = Intent().apply {
            action = "com.android.music.musicservicecommand.CMDPLAY"
            putExtra("command", "CMDPLAY")
        }
        context.startService(intent)
    }
}