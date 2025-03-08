package com.polovnitelefoni.network

import kotlinx.serialization.Serializable

@Serializable
data class Query<T>(
    val key: String,
    val value: T
)