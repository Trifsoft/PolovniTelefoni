package com.polovnitelefoni.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import com.polovnitelefoni.expect.Toast
import com.polovnitelefoni.icons.Battery1Bar
import com.polovnitelefoni.icons.Battery3Bar
import com.polovnitelefoni.icons.Battery5Bar
import com.polovnitelefoni.icons.BatteryAlert
import com.polovnitelefoni.icons.BatteryFull
import com.polovnitelefoni.icons.BatteryUnknown
import com.polovnitelefoni.icons.FilterList
import com.polovnitelefoni.icons.Memory
import com.polovnitelefoni.icons.SdStorage
import com.polovnitelefoni.model.Ad
import com.polovnitelefoni.model.Condition
import com.polovnitelefoni.values.Colors
import com.polovnitelefoni.values.Dimen
import com.polovnitelefoni.view.composable.AppFAB
import com.polovnitelefoni.view.composable.AppToolbar
import com.polovnitelefoni.view.composable.BrandModel
import com.polovnitelefoni.view.composable.Price
import com.polovnitelefoni.viewmodel.BrowseScreenModel
import com.skydoves.landscapist.coil3.CoilImage

object BrowseScreen: Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = { BrowseTopAppBar()},
            content = { AdList() }
        )
    }

    @Composable
    fun AdList() {
        val screenModel = rememberScreenModel{ BrowseScreenModel() }
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Dimen.spacing),
            contentPadding = PaddingValues(Dimen.spacing),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(screenModel.ads) { ad ->
                AdItem(ad)
            }
        }
    }

    @Composable
    fun AdItem(
        ad: Ad
    ) {
        val screenModel = rememberScreenModel { BrowseScreenModel() }
        val backgroundColor = if(ad.pushed) Colors.gold else Color.White
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimen.spacing),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(Dimen.contentPadding)
                .background(backgroundColor)
                .padding(Dimen.contentPadding)
        ) {
            if(screenModel.columnSize > 0) {
                AdImage(ad.photoUrls.first())
            }
            else {
                Spacer(Modifier.weight(1f))
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(Dimen.contentPadding),
                modifier = Modifier.onSizeChanged { size ->
                    if(screenModel.columnSize == 0) {
                        screenModel.columnSize = size.height
                    }
                },
            ) {
                ModelName(ad.model.fullName())
                Location(ad.location.name)
                BatteryHealth(ad.batteryHealth)
                ExternalStorage(ad.model.externalStorage)
                RAM(ad.model.ram)
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Condition(ad.condition)
                    Price(
                        formatedPrice = ad.formatedPrice()
                    )
                }
            }
        }
    }

    @Composable
    fun Condition(
        condition: Condition
    ) {
        val conditionText = when(condition) {
            Condition.NEW -> "Novo"
            Condition.LIKE_NEW -> "Kao novo"
            Condition.EXCELLENT -> "Odlično"
            Condition.GOOD -> "Dobro"
            Condition.FAIR -> "Solidno"
            Condition.DAMAGED -> "Oštećeno"
            else -> "Nepoznato"
        }
        AdProperty(
            iconVector = Icons.Default.Build,
            text = conditionText
        )
    }

    @Composable
    fun Location(
        locationName: String
    ) { AdProperty(
        iconVector = Icons.Default.LocationOn,
        text = locationName
    ) }

    @Composable
    fun RAM(
        ram: Int
    ) { AdProperty(
        iconVector = Icons.Filled.Memory,
        text = "$ram GB"
    ) }

    @Composable
    fun ExternalStorage(
        externalStorage: Int
    ) { AdProperty(
        iconVector = Icons.Filled.SdStorage,
        text = "$externalStorage GB"
    ) }

    @Composable
    fun BatteryHealth(batteryHealth: Int) {
        val batteryIconVector = when(batteryHealth) {
            in 0..60 -> Icons.Filled.BatteryAlert
            in 61..70 -> Icons.Filled.Battery1Bar
            in 71..80 -> Icons.Filled.Battery3Bar
            in 81..90 -> Icons.Filled.Battery5Bar
            in 91..100 -> Icons.Filled.BatteryFull
            else -> Icons.AutoMirrored.Filled.BatteryUnknown
        }
        val batteryText = when(batteryHealth) {
            in 0..60 -> "$batteryHealth% (Veoma loše)"
            in 61..70 -> "$batteryHealth% (Loše)"
            in 71..80 -> "$batteryHealth% (Solidno)"
            in 81..90 -> "$batteryHealth% (Dobro)"
            in 91..100 -> "$batteryHealth% (Odlično)"
            else -> "Nepoznato"
        }
        AdProperty(
            iconVector = batteryIconVector,
            text = batteryText
        )
    }

    @Composable
    fun AdProperty(
        iconVector: ImageVector,
        text: String
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Dimen.contentPadding2)
        ) {
            Icon(
                imageVector = iconVector,
                contentDescription = null
            )
            Text(text)
        }
    }

    @Composable
    fun ModelName(
        text: String
    ) { BrandModel(
        text = text,
        fontSize = 18.sp
    ) }

    @Composable
    private fun AdImage(url: String) {
        val screenModel = rememberScreenModel { BrowseScreenModel() }
        val height = with(LocalDensity.current) {
            screenModel.columnSize.toDp()
        }
        CoilImage(
            imageModel = { url },
            modifier = Modifier
                .height(height)
                .aspectRatio(3f/4)
        )
    }

    @Composable
    fun BrowseTopAppBar() {
        AppToolbar("Pretraga")
    }

}