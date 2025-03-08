package com.polovnitelefoni.config

import com.polovnitelefoni.model.Brand
import com.polovnitelefoni.model.Model

object Config {
    var brands: List<Brand> = listOf(
        Brand(id=1, name="Samsung", models = listOf(
            Model(id=1, name = "Samsung Galaxy S25"),
            Model(id=2, name = "Samsung Galaxy S25+"),
            Model(id=3, name = "Samsung Galaxy S25 Ultra"),
            Model(id=4, name = "Samsung Galaxy ZFold 6")
        )),
        Brand(id=2, name="Apple", models = listOf(
            Model(id=5, name = "iPhone 16"),
            Model(id=6, name = "iPhone 16 Pro"),
            Model(id=7, name = "iPhone 16 Pro Max"),
            Model(id=8, name = "iPhone 16e")
        )),
    )
}