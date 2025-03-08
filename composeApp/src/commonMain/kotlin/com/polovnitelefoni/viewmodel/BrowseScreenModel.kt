package com.polovnitelefoni.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import com.polovnitelefoni.model.Ad
import com.polovnitelefoni.model.Location
import com.polovnitelefoni.model.Model

class BrowseScreenModel: ScreenModel {
    val ads = listOf<Ad>(
        Ad(
            id=0,
            photoUrls = listOf("https://images.pexels.com/photos/1786433/pexels-photo-1786433.jpeg"),
            model = Model(
                id=0,
                brandName = "Samsung",
                name = "Galaxy S25 Ultra",
                externalStorage = 512,
                ram = 12
            ),
            pushed = true,
            location = Location(
                name = "Beograd"
            ),
            batteryHealth = 75,
            price = 1234
        )
    )
    var columnSize by mutableIntStateOf(0)
}