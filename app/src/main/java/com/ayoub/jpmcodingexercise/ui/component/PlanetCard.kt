package com.ayoub.jpmcodingexercise.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ayoub.jpmcodingexercise.R
import com.ayoub.jpmcodingexercise.domain.model.Planet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanetCard(
    modifier: Modifier = Modifier,
    planet: Planet,
    onPlanetClick: (String) -> Unit
) {
    Box(modifier = modifier.padding(top = 8.dp)) {
        ElevatedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                onPlanetClick.invoke(planet.id)
            }
        ) {
            Column (
                modifier= Modifier.padding(8.dp),
            ) {
                ItemRow(stringResource(id = R.string.name), planet.name)
                ItemRow(stringResource(id = R.string.terrain), planet.terrain)
                ItemRow(stringResource(id = R.string.climate), planet.climate)
            }
        }
    }
}

@Composable
fun ItemRow(name: String, value: String?) {
    Row {
        Text(
            text = "$name :",
            modifier= Modifier.padding(end=8.dp), fontWeight = FontWeight.SemiBold
        )
        Text(text = "$value", maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}


@Preview
@Composable
private fun PreviewPlanetCard() {
    PlanetCard(onPlanetClick = {}, planet = Planet(
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