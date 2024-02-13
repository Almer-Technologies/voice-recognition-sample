package com.almer.voicerecognitionsample

import android.content.Context
import android.widget.Toast
import com.almer.voice.recognition.bridge.SpeechListener
import com.almer.voice.recognition.bridge.VoiceCommand
import com.almer.voice.recognition.bridge.registerVoiceCommands
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class VoiceCommandsViewModel(private val context: Context): SpeechListener(context) {
    private val _state = MutableStateFlow(VoiceCommandsState())
    val state = _state.asStateFlow()

    //TODO: Make this "FUN"
    fun registerCommands(){
        context.registerVoiceCommands(listOf(VoiceCommand("Hello")))
    }
    init {
       registerCommands()
    }
    override fun onVoiceRecognitionStarted() {
        updateState { copy(isVoiceRecognitionOngoing = true) }
    }

    override fun onVoiceRecognitionStopped() {
        updateState { copy(isVoiceRecognitionOngoing = false) }
    }

    override fun onSpeechEvent(
        detectedSpeech: String,
        extraParameters: VoiceCommand.CallbackParams
    ) {
        Toast.makeText(
            context,
            "You said $detectedSpeech ${extraParameters.freeSpeech}",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun updateState(reducer: VoiceCommandsState.() -> VoiceCommandsState) {
        _state.update(reducer)
    }
}

data class VoiceCommandsState(val isVoiceRecognitionOngoing: Boolean = true)

