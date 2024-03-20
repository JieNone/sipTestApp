package ru.tyurin.siptest.ui.screens

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoScreen() {
    YouTubePlayer(videoId = "QKWAvLeayec&ab_channel=MARGO")
}


@SuppressLint("SetJavaScriptEnabled")
@Composable
fun YouTubePlayer(videoId: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                loadUrl("https://www.youtube.com/watch?v=$videoId")
            }
        }
    )
}
