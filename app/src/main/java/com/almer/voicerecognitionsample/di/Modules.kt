package com.almer.voicerecognitionsample.di

import com.almer.voicerecognitionsample.VoiceCommandsViewModel
import org.koin.dsl.module

val appModule = module {
    single { VoiceCommandsViewModel(get()) }
}