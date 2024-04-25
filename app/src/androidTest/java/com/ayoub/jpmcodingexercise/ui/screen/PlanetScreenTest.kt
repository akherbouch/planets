package com.ayoub.jpmcodingexercise.ui.screen

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.ayoub.jpmcodingexercise.R
import com.ayoub.jpmcodingexercise.planets
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test

class PlanetScreenTest {

    @get:Rule
    val rule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun test_display_name(){
        rule.setContent {
            val data= flowOf(PagingData.from(planets)).collectAsLazyPagingItems()
            PlanetsScreen(planetPagingItems = data, onPlanetClick = {})
        }
        rule.onNodeWithText(planets.first().name!!).assertExists()
    }

    @Test
    fun test_display_error(){
        rule.setContent {
            val data= flowOf(
                PagingData.from(
                    planets,
                    LoadStates(
                        refresh = LoadState.Error(Throwable("error")),
                        append = LoadState.Error(Throwable("error")),
                        prepend = LoadState.Error(Throwable("error"))
                    )
                )
            ).collectAsLazyPagingItems()
            PlanetsScreen(planetPagingItems = data, onPlanetClick = {})
        }
        val errorText = rule.activity.getString(R.string.fetching_planets_error)
        rule.onNodeWithText(errorText, ignoreCase = true).assertExists()
    }

    @Test
    fun test_display_loading(){
        rule.setContent {
            val data= flowOf(
                PagingData.from(
                    planets,
                    LoadStates(
                        refresh = LoadState.Loading,
                        append = LoadState.Error(Throwable("error showing")),
                        prepend = LoadState.Error(Throwable("error showing"))
                    )
                )
            ).collectAsLazyPagingItems()
            PlanetsScreen(planetPagingItems = data, onPlanetClick = {})
        }
        val fetchingText = rule.activity.getString(R.string.fetching)
        rule.onNodeWithText(fetchingText, ignoreCase = true).assertExists()
    }
}

