package com.ayoub.jpmcodingexercise.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayoub.jpmcodingexercise.R
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.ui.viewmodels.PlanetDetailViewModel


@Composable
fun PlanetDetailScreenRoute(
    viewModel: PlanetDetailViewModel,
    onBackClick: () -> Unit,
) {
    val planet by viewModel.planet.collectAsState()
    PlanetDetailScreen(
        planet = planet,
        onBackClick = onBackClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlanetDetailScreen(
    planet: Planet,
    onBackClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.planet_detials_screen_title))
            },
                navigationIcon = {
                    IconButton(onClick = { onBackClick.invoke()}) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                })
        }
    ) {
        Box(modifier = Modifier
            .padding(it)
            .padding(8.dp)) {
            ElevatedCard {
                Column(
                    modifier = Modifier.padding(8.dp),
                ) {
                    PlanetDetailItem(stringResource(id = R.string.name), planet.name)
                    PlanetDetailItem(stringResource(id = R.string.rotation_period), planet.rotationPeriod, false)
                    PlanetDetailItem(stringResource(id = R.string.orbital_period), planet.orbitalPeriod)
                    PlanetDetailItem(stringResource(id = R.string.diameter), planet.diameter, false)
                    PlanetDetailItem(stringResource(id = R.string.climate), planet.climate)
                    PlanetDetailItem(stringResource(id = R.string.gravity), planet.gravity, false)
                    PlanetDetailItem(stringResource(id = R.string.terrain), planet.terrain)
                    PlanetDetailItem(stringResource(id = R.string.surface_water), planet.surfaceWater, false)
                    PlanetDetailItem(stringResource(id = R.string.population), planet.population)
                }
            }
        }
    }
}

@Composable
fun PlanetDetailItem(
    name: String,
    value: String?,
    isDarkBackground: Boolean = true
) {
    value?.let {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(
                    if (isDarkBackground) Color.Transparent else MaterialTheme.colorScheme.primary.copy(
                        0.2f
                    ),
                    RoundedCornerShape(0.5f)
                )
                .fillMaxWidth()
        ){
            Text(
                modifier= Modifier.padding(8.dp),
                text = "$name : ",
            )
            Text(text = it)
        }
    }
}

@Preview
@Composable
private fun PreviewPlanetDetail() {
    PlanetDetailScreen(onBackClick = {}, planet = Planet(
        id = "1",
        name = "Tatooine",
        rotationPeriod = "23",
        orbitalPeriod = "304",
        diameter = "10465",
        climate = "arid",
        gravity = "1 standard",
        terrain = "desert",
        surfaceWater = "1",
        population = "200000",
        created = "2014-12-09T13:50:49.641000Z"
    ))
}