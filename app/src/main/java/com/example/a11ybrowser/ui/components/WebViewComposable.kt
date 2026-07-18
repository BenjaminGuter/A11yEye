package com.example.a11ybrowser.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.runtime.remember
import androidx.activity.compose.BackHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.LaunchedEffect
import com.example.a11ybrowser.data.model.A11yProfile
import com.example.a11ybrowser.util.ContentInjector

@Composable
fun WebViewComposable(
    url: String,
    profile: A11yProfile
) {
    val context = LocalContext.current
    val webView = remember {
        WebView(context).apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.builtInZoomControls = true
        }
    }

    // WebViewClient der bei jedem Seitenladen die Styles anwendet
    LaunchedEffect(profile) {
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Warte kurz, dann injiziere
                view?.postDelayed({
                    ContentInjector.applyProfile(webView, profile)
                }, 100)
            }
        }
        // Bei Profil-Änderung: Seite neu laden um Styles anzuwenden
        if (webView.url != null) {
            webView.reload()
        }
    }

    AndroidView(
        factory = { webView }
    )

    LaunchedEffect(url) {
        webView.loadUrl(url)
    }

    BackHandler(enabled = webView.canGoBack()) {
        webView.goBack()
    }
}