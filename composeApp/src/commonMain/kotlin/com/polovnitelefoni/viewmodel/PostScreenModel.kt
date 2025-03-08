package com.polovnitelefoni.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.mohamedrejeb.calf.core.PlatformContext
import com.mohamedrejeb.calf.io.KmpFile
import com.mohamedrejeb.calf.io.readByteArray
import com.polovnitelefoni.model.Model
import kotlinx.coroutines.launch

class PostScreenModel: ScreenModel {
    private var selectedModelId by mutableIntStateOf(0)
    var ram = mutableIntStateOf(0)
    var storage = mutableIntStateOf(0)
    var batteryHealth = mutableIntStateOf(0)
    private var price = mutableIntStateOf(0)
    var pickedPhotos = mutableStateListOf<ByteArray>()

    fun isModelSelected(model: Model): Boolean =
        selectedModelId == model.id

    fun selectModel(model: Model) {
        selectedModelId = model.id
    }

    fun updatePickedPhotos(context: PlatformContext, kmpFiles: List<KmpFile>) {
        screenModelScope.launch {
           kmpFiles.forEach { pickedPhotos.add(it.readByteArray(context)) }
        }
    }

}