package com.polovnitelefoni.model

data class Ad(
    val id: Int = 0,
    val model: Model = Model(),
    val price: Int = 0,
    val photoUrls: List<String> = listOf(),
    val location: Location = Location(),
    val batteryHealth: Int = -1,
    val profileId: Int = 0,
    val condition: Condition = Condition.UNKNOWN,
    val pushed: Boolean = false
) {
    fun formatedPrice(): String {
        return price.toString()
            .reversed()
            .chunked(3)
            .joinToString(".")
            .reversed()
    }
}
