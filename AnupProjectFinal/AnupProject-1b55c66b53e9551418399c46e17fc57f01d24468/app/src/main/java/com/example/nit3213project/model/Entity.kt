package com.example.nit3213project.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Entity(
    val artistName: String,
    val albumTitle: String,
    val releaseYear: Int,
    val genre: String,
    val trackCount: Int,
    val description: String,
    val popularTrack: String,
    val imageResId: Int // Add this field to store the image resource ID
) : Parcelable