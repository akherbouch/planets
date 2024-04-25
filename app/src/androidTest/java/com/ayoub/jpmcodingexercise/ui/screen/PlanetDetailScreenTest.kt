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
    fun displayName() {
        rule.setContent { PlanetDetailScreen(planet = testPlanet, onBackClick = {}) }
        rule.onNodeWithText(testPlanet.name!!).assertExists()
    }

    @Test
    fun displayRotationPeriod() {
        rule.setContent { PlanetDetailScreen(planet = testPlanet, onBackClick = {}) }
        rule.onNodeWithText(testPlanet.rotationPeriod!!).assertExists()
    }

    @Test
    fun displayOrbitalPeriod() {
        rule.setContent { PlanetDetailScreen(planet = testPlanet, onBackClick = {}) }
        rule.onNodeWithText(testPlanet.orbitalPeriod!!).assertExists()
    }

}

