package com.example.nit3213project
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class LoginRequest(
    val username: String,
    val password: String
)

data class LoginResponse(
    val keypass: String
)

data class DashboardResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)
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
