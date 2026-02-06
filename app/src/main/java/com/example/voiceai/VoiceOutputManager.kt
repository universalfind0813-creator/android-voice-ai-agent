package com.example.voiceai

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

class VoiceOutputManager(private val context: Context) : TextToSpeech.OnInitListener {
    private var textToSpeech: TextToSpeech? = null
    private var isReady = false

    init {
        textToSpeech = TextToSpeech(context, this)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech?.language = Locale.getDefault()
            isReady = true
        }
    }

    fun speak(text: String) {
        if (isReady && textToSpeech != null) {
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    fun stop() {
        if (textToSpeech != null) {
            textToSpeech?.stop()
        }
    }

    fun shutdown() {
        if (textToSpeech != null) {
            textToSpeech?.shutdown()
            textToSpeech = null
        }
    }

    fun isSpeaking(): Boolean {
        return textToSpeech?.isSpeaking ?: false
    }
}