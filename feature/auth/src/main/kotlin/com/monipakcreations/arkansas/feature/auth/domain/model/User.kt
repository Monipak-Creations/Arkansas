package com.monipakcreations.arkansas.feature.auth.domain.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val imageUrl: String,
) {
    val fullName: String get() = "$firstName $lastName".trim()
}
