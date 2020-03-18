package com.ahrybuk.opencvtest.data.models

import android.graphics.Bitmap

data class Session(
    val mainImage: Bitmap,
    val faceDetectedImage: Bitmap,
    val timestamp: Long
)