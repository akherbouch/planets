package com.ayoub.jpmcodingexercise.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ayoub.jpmcodingexercise.R
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.ui.component.ErrorMessage
import com.ayoub.jpmcodingexercise.ui.component.FetchingIndicator
import com.ayoub.jpmcodingexercise.ui.component.ItemLoading
import com.ayoub.jpmcodingexercise.ui.component.PlanetCard
import com.ayoub.jpmcodingexercise.ui.viewmodels.PlanetsViewModel

@Composable
fun PlanetsScreenRoute(
    viewModel: PlanetsViewModel,
    onPlanetClick: (String) -> Unit,
) {
    val planetPagingItems = viewModel.planets.collectAsLazyPagingItems()
    PlanetsScreen(
        planetPagingItems = planetPagingItems,
        onPlanetClick = onPlanetClick,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlanetsScreen(
    planetPagingItems: LazyPagingItems<Planet>,
    onPlanetClick: (String) -> Unit,
) {
   Scaffold (
       topBar = {
           TopAppBar(title = {
               Text(text = stringResource(id = R.string.planets_screen_title))
           })
       }
   ){
       LazyColumn(
           modifier = Modifier
               .padding(it)
               .padding(horizontal = 16.dp, vertical = 4.dp)
       ) {

           items(planetPagingItems.itemCount) { index ->
               planetPagingItems[index]?.let { planet ->
                   PlanetCard(
                       planet = planet,
                       onPlanetClick = onPlanetClick
                   )
               }
           }

           planetPagingItems.apply {
               when {
                   loadState.refresh is LoadState.Loading -> {
                       item { FetchingIndicator(modifier = Modifier.fillParentMaxSize()) }
                   }

                   loadState.refresh is LoadState.Error -> {
                       item {
                           ErrorMessage(
                               modifier = Modifier.fillParentMaxSize(),
                               message = stringResource(id = R.string.fetching_planets_error),
                               onClickRetry = { retry() }
                           )
                       }
                   }

                   loadState.append is LoadState.Loading -> {
                       item { ItemLoading() }
                   }

                   loadState.append is LoadState.Error -> {
                       item {
                           ErrorMessage(
                               modifier = Modifier,
                               message = stringResource(id = R.string.append_planets_error),
                               onClickRetry = { retry() })
                       }
                   }
               }
           }
       }
   }
}

