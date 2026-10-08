package com.monipakcreations.arkansas.core.network

/** Thrown when the API answers with a non-2xx status. */
class NetworkException(
    val statusCode: Int,
    override val message: String,
) : Exception(message)
