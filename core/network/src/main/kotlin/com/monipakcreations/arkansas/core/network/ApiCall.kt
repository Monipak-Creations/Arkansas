package com.monipakcreations.arkansas.core.network

import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException

/** Runs a Ktor request and converts HTTP failures into [NetworkException]. */
suspend inline fun <T> apiCall(block: () -> T): T =
    try {
        block()
    } catch (e: ResponseException) {
        throw e.toNetworkException()
    }

suspend fun ResponseException.toNetworkException(): NetworkException {
    val message = runCatching { response.body<NetworkError>().message }.getOrNull()
    return NetworkException(response.status.value, message ?: response.status.description)
}
