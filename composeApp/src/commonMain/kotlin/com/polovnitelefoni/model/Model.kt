package com.polovnitelefoni.model

data class Model (
    val id: Int = 0,
    val brandName: String = "",
    val name: String = "",
    val releaseYear: Int = 0,
    val externalStorage: Int = 0,
    val ram: Int = 0,
) {
    fun fullName() = "$brandName $name"
}