package com.polovnitelefoni.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.polovnitelefoni.expect.Toast
import com.polovnitelefoni.model.Ad
import com.polovnitelefoni.values.Colors
import com.polovnitelefoni.values.Dimen
import com.polovnitelefoni.view.composable.AppFAB
import com.polovnitelefoni.view.composable.BrandModel
import com.polovnitelefoni.view.composable.HeaderText
import com.polovnitelefoni.view.composable.Price
import com.polovnitelefoni.viewmodel.MainScreenModel
import com.skydoves.landscapist.coil3.CoilImage

object MainScreen: Screen {

    @Composable
    override fun Content() {
        Scaffold(
            topBar = { MainTopAppBar() },
            floatingActionButton = { MainFloatingActionButton() },
            content = { GridSections(it) }
        )
    }

    @Composable
    private fun MainFloatingActionButton() {
        val navigator = LocalNavigator.currentOrThrow
        AppFAB(
            imageVector = Icons.Default.Add,
            onClick = { navigator.push(PostScreen)}
        )
    }

    @Composable
    private fun GridSections(innerPadding: PaddingValues) {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = rememberScreenModel { MainScreenModel() }
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(Dimen.spacing),
            horizontalArrangement = Arrangement.spacedBy(Dimen.spacing),
            contentPadding = PaddingValues(Dimen.spacing),
            modifier = Modifier
                .fillMaxSize()
        ) {
            gridSection(
                sectionTitle = "Pogledaj oglase koje si sačuvao",
                endText = "Vidi sve",
                ads = screenModel.savedAds,
                onEndClick = {
                    Toast("Vidi sve")
                }
            )
            gridSection(
                sectionTitle = "Novi oglasi",
                endText = "Detaljna pretraga",
                ads = screenModel.newAds,
                onEndClick = { navigateToSearch(navigator) }
            )
        }
    }

    private fun navigateToSearch(navigator: Navigator) {
        navigator.push(SearchScreen)
    }

    private fun LazyGridScope.gridSection(
        sectionTitle: String,
        ads: List<Ad>,
        endText: String,
        onEndClick: ()->Unit
    ) {
        if(ads.isNotEmpty()) {
            item(span = { GridItemSpan(2) }) {
                HeaderText(sectionTitle)
            }
            items(
                items = ads
            ) {
                AdItem(it)
            }
            item(span = { GridItemSpan(2) }) {
                TextButton(
                    onClick = onEndClick
                ) {
                    Text(
                        text = endText,
                        color = MaterialTheme.colors.secondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }

    @Composable
    private fun MainTopAppBar() {
        val navigator = LocalNavigator.currentOrThrow
        TopAppBar(
            title = { Text("Logo") },
            actions = {
                AppBarIcon(
                    imageVector = Icons.Default.Search,
                    onClick = { navigateToSearch(navigator) }
                )
                AppBarIcon(
                    imageVector = Icons.Default.AccountCircle,
                    onClick = { Toast("Profile")}
                )
                AppBarIcon(
                    imageVector = Icons.AutoMirrored.Default.Send,
                    onClick = { Toast("Message")}
                )
            }
        )
    }

    @Composable
    private fun AppBarIcon(
        imageVector: ImageVector,
        onClick: ()->Unit
    ) {
        IconButton(
            onClick = onClick
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = null
            )
        }
    }

    @Composable
    private fun AdItem(ad: Ad) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                Dimen.contentPadding
            ),
            modifier = Modifier
                .shadow(Dimen.spacing)
                .background(
                    if(ad.pushed)
                        Colors.gold
                    else Color.White
                )
                .padding(Dimen.contentPadding)
        ) {
            AdImage(ad.photoUrls.first())
            BrandName(ad.model.brandName)
            ModelName(ad.model.name)
            Price(ad.formatedPrice())
        }
    }

    @Composable
    private fun AdImage(
        url: String
    ) {
        CoilImage(
            imageModel = { url },
            modifier = Modifier
                .aspectRatio(3f/4)
        )
    }

    @Composable
    private fun BrandName(text: String) {
        BrandModel(text.uppercase(), 16.sp)
    }

    @Composable
    private fun ModelName(text: String) {
        BrandModel(text, 14.sp)
    }
}