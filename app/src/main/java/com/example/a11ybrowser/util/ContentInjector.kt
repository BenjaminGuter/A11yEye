package com.example.a11ybrowser.util

import android.webkit.WebView
import com.example.a11ybrowser.data.model.A11yProfile

object ContentInjector {

    fun applyProfile(webView: WebView, profile: A11yProfile) {
        val css = buildCSS(profile)
        val js = buildJS(profile)

        webView.evaluateJavascript(
            """
            (function() {
                // Remove old style
                var oldStyle = document.getElementById('a11y-style');
                if (oldStyle) oldStyle.remove();
                
                // Inject new CSS
                var style = document.createElement('style');
                style.id = 'a11y-style';
                style.textContent = `$css`;
                document.head.appendChild(style);
                
                // Execute JS
                $js
            })();
            """.trimIndent(),
            null
        )
    }

    private fun buildCSS(profile: A11yProfile): String {
        // Alle Filter KOMBINIEREN statt überschreiben!
        val filters = mutableListOf<String>()

        // Brightness/Contrast/Saturation IMMER hinzufügen
        filters.add("brightness(${1 + profile.brightness * 0.25})")
        filters.add("contrast(${1 + profile.contrast * 0.25})")
        filters.add("saturate(${1 + profile.saturation * 0.33})")

        // Grayscale wenn aktiviert
        if (profile.grayscale) {
            filters.add("grayscale(100%)")
        }

        // Invert wenn aktiviert
        if (profile.invertColors) {
            filters.add("invert(1)")
            filters.add("hue-rotate(180deg)")
        }

        val combinedFilters = filters.joinToString(" ")

        return """
            body {
                font-size: ${profile.fontSize * 100}% !important;
                line-height: ${profile.lineHeight} !important;
                letter-spacing: ${profile.letterSpacing}em !important;
            }
            html {
                filter: $combinedFilters !important;
            }
            ${if (profile.dyslexicFont) "* { font-family: 'OpenDyslexic', sans-serif !important; }" else ""}
            ${if (profile.highlightLink) "a { background-color: yellow !important; border: 2px solid red !important; padding: 2px !important; }" else ""}
            ${if (profile.hideImage) "img { display: none !important; }" else ""}
        """.trimIndent()
    }

    private fun buildJS(profile: A11yProfile): String {
        return """
            ${if (profile.stopAnimations) "document.querySelectorAll('*').forEach(el => { el.style.animation = 'none'; el.style.transition = 'none'; });" else ""}
            ${if (profile.muteAudio) "document.querySelectorAll('audio, video').forEach(el => el.muted = true);" else ""}
        """.trimIndent()
    }
}