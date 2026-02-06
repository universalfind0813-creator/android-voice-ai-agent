package com.example.voiceai

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import java.util.Locale

class VoiceInputManager(private val context: Context) {
    private var speechRecognizer: SpeechRecognizer? = null
    private var isListening = false
    private var onResultListener: ((String) -> Unit)? = null
    private var onErrorListener: ((String) -> Unit)? = null

    init {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    }

    fun startListening(onResult: (String) -> Unit, onError: (String) -> Unit) {
        if (isListening) return

        onResultListener = onResult
        onErrorListener = onError
        isListening = true

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Listening...")
        }

        speechRecognizer?.startListening(intent)
    }

    fun stopListening() {
        isListening = false
        speechRecognizer?.stopListening()
    }

    fun destroy() {
        speechRecognizer?.destroy()
        speechRecognizer = null
    }

    fun isCurrentlyListening(): Boolean = isListening
}