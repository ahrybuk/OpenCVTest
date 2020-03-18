package com.ahrybuk.opencvtest.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sessions")
data class SessionEntity(
    @PrimaryKey val timestamp: Long,
    @ColumnInfo(name = "main_image_path") val mainImagePath: String,
    @ColumnInfo(name = "face_detected_image_path") val faceDetectedImagePath: String
)