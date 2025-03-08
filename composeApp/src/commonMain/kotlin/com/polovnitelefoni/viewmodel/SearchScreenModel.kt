package com.polovnitelefoni.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import com.polovnitelefoni.model.Brand
import com.polovnitelefoni.model.Model

class SearchScreenModel: ScreenModel {
    private val selectedModelIds = mutableStateMapOf<Int, Boolean>()
    var ramMinValue = mutableStateOf<Int?>(null)
    var ramMaxValue = mutableStateOf<Int?>(null)
    var storageMinValue = mutableStateOf<Int?>(null)
    var storageMaxValue = mutableStateOf<Int?>(null)
    var batteryHealthMinValue = mutableStateOf<Int?>(null)
    var batteryHealthMaxValue = mutableStateOf<Int?>(null)
    var priceMinValue = mutableStateOf<Int?>(null)
    var priceMaxValue = mutableStateOf<Int?>(null)

    private fun selectBrand(brand: Brand) {
        brand.models.forEach{ model ->
            selectedModelIds[model.id] = true
        }
    }
    fun deselectBrand(brand: Brand) {
        brand.models.forEach{ model ->
            selectedModelIds[model.id] = false
        }
    }
    fun isBrandSelected(brand: Brand) =
        brand.models.all { model -> selectedModelIds[model.id] == true }
    fun onModelCheckedChange(model: Model, checked: Boolean) {
        selectedModelIds[model.id] = checked
    }

    fun isModelSelected(model: Model): Boolean =
        selectedModelIds[model.id] == true

    fun onBrandCheckedChange(brand: Brand, checked: Boolean) {
        if(checked) {
            selectBrand(brand)
        }
    }

    var modelsExpanded by mutableStateOf(false)
    fun onModelsExpanded() {
        modelsExpanded = !modelsExpanded
    }

    fun setRangeValue(
        mutableState: MutableState<Int?>,
        value: String,
        min: Int?, max: Int?,
    ) {
        if(value == "") {
            mutableState.value = null
        }
        else {
            val numValue = value.trim().toInt()
            if(min != null && numValue < min) {
                mutableState.value = min
            }
            else if(max != null && numValue > max) {
                mutableState.value = max
            }
            else {
                mutableState.value = numValue
            }
        }
    }

    val arrowRotateDegrees by derivedStateOf {
        if(modelsExpanded) 0f else -90f
    }


}