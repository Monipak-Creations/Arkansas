package com.monipakcreations.arkansas.feature.auth.data.remote.dto

import kotlinx.serialization.Serializable

/** Response of `POST https://dummyjson.com/auth/login`. */
@Serializable
data class LoginResponseDto(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String,
)
