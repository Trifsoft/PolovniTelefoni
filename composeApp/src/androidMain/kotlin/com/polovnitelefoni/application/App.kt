package com.polovnitelefoni.application

import android.app.Application

class App : Application() {
    companion object {
        private lateinit var instance: App
        fun getInstance() = instance
    }
    init {
        instance = this
    }
}