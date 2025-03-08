package com.polovnitelefoni.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.polovnitelefoni.config.Config
import com.polovnitelefoni.model.Brand
import com.polovnitelefoni.model.Model
import com.polovnitelefoni.values.Dimen
import com.polovnitelefoni.view.composable.AppFAB
import com.polovnitelefoni.view.composable.AppToolbar
import com.polovnitelefoni.view.composable.HeaderText
import com.polovnitelefoni.viewmodel.SearchScreenModel

object SearchScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { SearchScreenModel() }
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            topBar = {
                AppToolbar("Pretraga")
            },
            floatingActionButton = {
                AppFAB(
                    imageVector = Icons.Default.Search,
                    onClick = { navigator.push(BrowseScreen) }
                )
            }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(Dimen.spacing),
                verticalArrangement = Arrangement.spacedBy(Dimen.spacing),
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    ExpanderHeader()
                }
                if(screenModel.modelsExpanded) {
                    models()
                }
                item {
                    RangeInput(
                        title = "Cena:",
                        from = screenModel.priceMinValue,
                        unit = "EUR",
                        to = screenModel.priceMaxValue,
                    )
                }
                item {
                    RangeInput(
                        title = "RAM memorija:",
                        from = screenModel.ramMinValue,
                        unit = "GB",
                        to = screenModel.ramMaxValue
                    )
                }
                item {
                    RangeInput(
                        title = "Spoljašnja memorija:",
                        from = screenModel.storageMinValue,
                        unit = "GB",
                        to = screenModel.storageMaxValue
                    )
                }
                item {
                    RangeInput(
                        title = "Zdravlje baterije:",
                        from = screenModel.batteryHealthMinValue,
                        unit = "%",
                        to = screenModel.batteryHealthMaxValue,
                        toMax = 100
                    )
                }
            }
        }
    }

    @Composable
    fun ExpanderHeader() {
        val screenModel = rememberScreenModel { SearchScreenModel() }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = screenModel::onModelsExpanded)
        ) {
            HeaderText("Izaberite modele telefona:")
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier
                    .rotate(screenModel.arrowRotateDegrees)
            )
        }
    }

    @Composable
    fun RangeInput(
        title: String,
        from: MutableState<Int?>,
        to: MutableState<Int?>,
        unit: String,
        fromMin: Int? = null,
        toMax: Int? = null
    ) {
        val screenModel = rememberScreenModel { SearchScreenModel() }
        Column {
            HeaderText(
                text = title,
            )
            Row(
                horizontalArrangement = Arrangement
                    .spacedBy(Dimen.spacing),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                OutlinedTextField(
                    value = from.value?.toString() ?: "",
                    onValueChange = { value ->
                        screenModel.setRangeValue(
                            mutableState = from,
                            value = value,
                            min = fromMin,
                            max = toMax
                        )
                    },
                    placeholder = {
                        Text("Od ($unit)")
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.weight(1f)
                        .background(Color.White)
                )
                OutlinedTextField(
                    value = to.value?.toString() ?: "",
                    onValueChange = { value ->
                        screenModel.setRangeValue(
                            mutableState = to,
                            value = value,
                            min = fromMin,
                            max = toMax
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Number
                    ),
                    placeholder = {
                        Text("Do ($unit)")
                    },
                    modifier = Modifier.weight(1f)
                        .background(Color.White)
                )
            }
        }
    }

    private fun LazyListScope.models() {
        Config.brands.forEach { brand ->
            item {
                BrandCheckbox(brand)
            }
            items(
                items = brand.models,
                key = { model -> model.id}
            ) { model ->
                ModelCheckbox(
                    model = model
                )
            }
        }
    }


    @Composable
    fun ModelCheckbox(
        model: Model
    ) {
        val screenModel = rememberScreenModel{ SearchScreenModel() }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(model.name)
            Checkbox(
                checked = screenModel.isModelSelected(model),
                modifier = Modifier
                    .size(24.dp),
                onCheckedChange = { checked ->
                    screenModel.onModelCheckedChange(model, checked)
                }
            )
        }
    }

    @Composable
    fun BrandCheckbox(brand: Brand) {
        val screenModel = rememberScreenModel{ SearchScreenModel()}
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = brand.name,
                fontSize = 18.sp
            )
            Spacer(Modifier.weight(1f))
            TextButton(
                onClick = { screenModel.deselectBrand(brand)}
            ) { Text("Resetuj")}
            Checkbox(
                enabled = !screenModel.isBrandSelected(brand),
                checked = screenModel.isBrandSelected(brand),
                interactionSource = null,
                modifier = Modifier
                    .size(24.dp),
                onCheckedChange = { checked ->
                    screenModel.onBrandCheckedChange(brand, checked)
                }
            )
        }
    }

}