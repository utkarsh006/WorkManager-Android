package com.example.quoteswork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.quoteswork.presentation.MainScreen
import com.example.quoteswork.presentation.MainViewModel
import com.example.quoteswork.ui.theme.QuotesWorkTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotesWorkTheme {
                Surface(modifier = Modifier.safeContentPadding()) {
                    val viewModel = hiltViewModel<MainViewModel>()
                    MainScreen(viewModel) {
                        viewModel.getQuote()
                    }
                }
            }
        }
    }
}