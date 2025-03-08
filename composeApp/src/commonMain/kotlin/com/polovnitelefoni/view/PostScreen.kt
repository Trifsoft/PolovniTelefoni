package com.polovnitelefoni.view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import com.polovnitelefoni.config.Config
import com.polovnitelefoni.expect.Toast
import com.polovnitelefoni.icons.AddAPhoto
import com.polovnitelefoni.icons.Memory
import com.polovnitelefoni.icons.SdStorage
import com.polovnitelefoni.model.Model
import com.polovnitelefoni.values.Dimen
import com.polovnitelefoni.view.composable.AppFAB
import com.polovnitelefoni.view.composable.AppToolbar
import com.polovnitelefoni.view.composable.HeaderText
import com.polovnitelefoni.viewmodel.PostScreenModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

object PostScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel{ PostScreenModel() }
        Scaffold(
            topBar = { AppToolbar("Postavite oglas")},
            floatingActionButton = { PostFAB() }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(Dimen.spacing),
                horizontalArrangement = Arrangement.spacedBy(Dimen.spacing),
                contentPadding = PaddingValues(Dimen.spacing)
            ) {
                headerText("Izaberite model vašeg telefona *")
                modelPicker()
                headerText("Spoljašnja memorija *")
                externalMemory()
                headerText("RAM *")
                ram()
                headerText("Zdravlje baterije")
                batteryHealth()
                headerText("Postavi slike")
                pickedPhotos(screenModel.pickedPhotos)
                addImage()
            }
        }
    }

    private fun LazyGridScope.addImage() {
        item {
            val screenModel = rememberScreenModel { PostScreenModel() }
            val context = LocalPlatformContext.current
            val pickerLauncher = rememberFilePickerLauncher(
                type = FilePickerFileType.Image,
                selectionMode = FilePickerSelectionMode.Multiple,
                onResult = { photos ->
                    screenModel.updatePickedPhotos(context, photos)
                }
            )
            Box(
                Modifier.aspectRatio(3f/4)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(Dimen.borderRadius)
                    )
                    .border(
                        width = Dimen.borderWidth,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(Dimen.borderRadius)
                    )
                    .clickable {
                        pickerLauncher.launch()
                    }
            ) {
                Icon(
                    imageVector = Icons.Filled.AddAPhoto,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.align(Alignment.Center)
                        .size(32.dp)
                )
            }
        }
    }
    private fun LazyGridScope.pickedPhotos(
        pickedPhotos: SnapshotStateList<ByteArray>
    ) {
        items(pickedPhotos) { photo ->
            Box(
                Modifier.aspectRatio(3f/4)
                    .border(
                        width = Dimen.borderWidth,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
                        shape = RoundedCornerShape(Dimen.borderRadius)
                    )
                    .background(Color.White, RoundedCornerShape(Dimen.borderRadius))
            ) {
                CoilImage(
                    imageModel = { photo },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop
                    ),
                    modifier = Modifier
                        .fillMaxSize()

                )
                IconButton(
                    onClick = { pickedPhotos.remove(photo) },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) { Icon(Icons.Default.Close, null)}
            }
        }
    }

    private fun LazyGridScope.headerText(
        text: String
    ) {
        item(
            span = { GridItemSpan(2)}
        ) { HeaderText(text) }
    }

    private fun LazyGridScope.batteryHealth() {
        item(
            span = { GridItemSpan(2)}
        ) {
            val screenModel = rememberScreenModel { PostScreenModel() }
            AdProperty(
                iconVector = Icons.Filled.Memory,
                placeholder = "Zdravlje baterije",
                unit = "%",
                mutableValue = screenModel.batteryHealth
            )
        }
    }

    private fun LazyGridScope.ram() {
        item(
            span = { GridItemSpan(2)}
        ) {
            val screenModel = rememberScreenModel { PostScreenModel() }
            AdProperty(
                iconVector = Icons.Filled.Memory,
                placeholder = "RAM memorija",
                unit = "GB",
                mutableValue = screenModel.ram
            )
        }
    }

    private fun LazyGridScope.externalMemory() {
        item(
            span = { GridItemSpan(2)}
        ) {
            val screenModel = rememberScreenModel { PostScreenModel() }
            AdProperty(
                iconVector = Icons.Filled.SdStorage,
                placeholder = "Spoljašnja memorija",
                unit = "GB",
                mutableValue = screenModel.storage
            )
        }
    }

    @Composable
    fun AdProperty(
        iconVector: ImageVector,
        placeholder: String,
        unit: String,
        mutableValue: MutableIntState
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimen.spacing),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = iconVector,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            OutlinedTextField(
                value = mutableValue.intValue.toString(),
                onValueChange = { value ->
                    mutableValue.intValue = if(value.isNotEmpty())
                        value.toInt()
                    else 0
                },
                placeholder = { Text("$placeholder ($unit)") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.background(Color.White)
            )
        }
    }

    private fun LazyGridScope.modelPicker() {
        Config.brands.forEach { brand ->
            item(
                span = { GridItemSpan(2)}
            ) { Brand(brand.name) }
            items(
                items = brand.models,
                span = { GridItemSpan(2) }
            ) { model ->
                ModelRadio(model = model)
            }
        }
    }

    @Composable
    fun ModelRadio(model: Model) {
        val screenModel = rememberScreenModel{ PostScreenModel() }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .clickable(
                    onClick = { screenModel.selectModel(model)},
                    indication = null,
                    interactionSource = null
                )
        ) {
            Text(model.name)
            RadioButton(
                selected = screenModel.isModelSelected(model),
                modifier = Modifier
                    .size(24.dp),
                onClick = { screenModel.selectModel(model)},
            )
        }
    }

    @Composable
    fun Brand(
        brand: String
    ) {
        Text(
            text = brand,
            fontWeight = FontWeight.SemiBold
        )
    }

    @Composable
    fun PostFAB() {
        AppFAB(
            imageVector = Icons.Default.Add,
            onClick = { Toast("Add")}
        )
    }
}