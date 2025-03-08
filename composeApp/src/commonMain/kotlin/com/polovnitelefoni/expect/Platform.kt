package com.polovnitelefoni.expect

enum class Platform {
    ANDROID,
    IOS
}

expect fun getPlatform(): Platform