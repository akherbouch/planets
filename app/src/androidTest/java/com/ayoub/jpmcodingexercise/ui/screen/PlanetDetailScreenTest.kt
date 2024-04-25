package com.ayoub.jpmcodingexercise.ui.screen

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.ayoub.jpmcodingexercise.planets
import org.junit.Rule
import org.junit.Test

class PlanetDetailScreenTest {

    @get:Rule
    val rule= createComposeRule()

    private val testPlanet = planets.first()

    @Test
    fun test_display_name() {
        rule.setContent { PlanetDetailScreen(planet = testPlanet, onBackClick = {}) }
        rule.onNodeWithText(testPlanet.name!!).assertExists()
    }

    @Test
    fun test_display_rotation_period() {
        rule.setContent { PlanetDetailScreen(planet = testPlanet, onBackClick = {}) }
        rule.onNodeWithText(testPlanet.rotationPeriod!!).assertExists()
    }

    @Test
    fun test_display_orbital_period() {
        rule.setContent { PlanetDetailScreen(planet = testPlanet, onBackClick = {}) }
        rule.onNodeWithText(testPlanet.orbitalPeriod!!).assertExists()
    }

}

