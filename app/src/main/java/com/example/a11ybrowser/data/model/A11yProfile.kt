package com.example.a11ybrowser.data.model

import java.util.UUID
import kotlinx.serialization.Serializable
@Serializable
data class A11yProfile(
    val id: String = UUID.randomUUID().toString(),
    val name: String,

    //Colors
    val contrast: Int = 0,
    val brightness: Int = 0,
    val saturation: Int = 0,
    val grayscale: Boolean = false,
    val invertColors: Boolean = false,

    //Content
    val fontSize: Float = 1f,
    val lineHeight: Float = 1f,
    val letterSpacing: Float = 0f,
    val dyslexicFont : Boolean = false,
    val highlightLink: Boolean = false,

    //Orientation
    val muteAudio: Boolean = false,
    val hideImage: Boolean = false,
    val readingMask: Boolean = false,
    val stopAnimations: Boolean = false,

    // Audio
    val ttsPitch: Float = 1f,
    val ttsSpeed: Float = 1f,
    val ttsEnabled: Boolean = false,
    val autoReadLinks: Boolean = false
    ){
    companion object {
        fun default() = A11yProfile(
            id = "default",
            name = "Standard"
        )
    }
}


