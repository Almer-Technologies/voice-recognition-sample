package com.almer.voicerecognitionsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.almer.voicerecognitionsample.ui.theme.VoiceRecognitionSampleTheme
import org.koin.androidx.compose.get
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VoiceRecognitionSampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

// TODO: Make this FUN
@Composable
fun MainScreen(viewModel: VoiceCommandsViewModel = koinInject()) {
    val state = viewModel.state.collectAsState().value
    Column {
        Text("Voice recognition is ongoing: ${state.isVoiceRecognitionOngoing}")
        Button(onClick = viewModel::registerCommands) {
            Text("Register commands")
        }
    }
}
