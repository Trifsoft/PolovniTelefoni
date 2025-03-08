package com.polovnitelefoni.viewmodel

import cafe.adriel.voyager.core.model.ScreenModel
import com.polovnitelefoni.model.Ad
import com.polovnitelefoni.model.Model

class MainScreenModel: ScreenModel {
    val savedAds = listOf(
        Ad(
            id = 0,
            model = Model(
                id = 0,
                brandName = "Samsung",
                name = "Galaxy S25 Ultra",
                releaseYear = 2025
            ),
            price = 1000,
            photoUrls = listOf("https://images.pexels.com/photos/1786433/pexels-photo-1786433.jpeg")
        ),
        Ad(
            id = 1,
            model = Model(
                id = 0,
                brandName = "Apple",
                name = "iPhone 16 Pro Max",
                releaseYear = 2025
            ),
            price = 1200,
            pushed = true,
            photoUrls = listOf("https://images.pexels.com/photos/1786433/pexels-photo-1786433.jpeg")
        ),
    )
    val newAds = listOf<Ad>()
}