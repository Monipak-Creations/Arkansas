package com.monipakcreations.arkansas.core.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkError(val message: String? = null)
