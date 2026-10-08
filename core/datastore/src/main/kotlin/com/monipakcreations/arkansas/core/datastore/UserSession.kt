package com.monipakcreations.arkansas.core.datastore

data class UserSession(
    val userId: Int,
    val accessToken: String,
    val refreshToken: String,
)
