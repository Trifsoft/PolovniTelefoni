package com.polovnitelefoni.expect

import android.widget.Toast
import com.polovnitelefoni.application.App

actual fun Toast(message: String) {
    Toast.makeText(App.getInstance(), message, Toast.LENGTH_SHORT).show()
}