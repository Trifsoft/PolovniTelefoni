package com.polovnitelefoni.model

data class Brand(
    val id: Int = 0,
    val name: String = "",
    val models: List<Model> = listOf()
    //TODO
)