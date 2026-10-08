package com.monipakcreations.arkansas.feature.auth.data.remote

import com.monipakcreations.arkansas.core.network.apiCall
import com.monipakcreations.arkansas.feature.auth.data.remote.dto.LoginRequest
import com.monipakcreations.arkansas.feature.auth.data.remote.dto.LoginResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

/** `POST https://dummyjson.com/auth/login` */
class AuthRemoteDataSource @Inject constructor(
    private val client: HttpClient,
) {
    suspend fun login(username: String, password: String): LoginResponseDto = apiCall {
        client.post("auth/login") {
            contentType(ContentType.Application.Json)
            setBody(LoginRequest(username = username, password = password))
        }.body<LoginResponseDto>()
    }
}
