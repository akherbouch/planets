package com.ayoub.jpmcodingexercise

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ayoub.jpmcodingexercise.domain.model.Planet
import com.ayoub.jpmcodingexercise.domain.repository.PlanetRepository
import com.ayoub.jpmcodingexercise.ui.theme.JpmCodingExerciseTheme
import com.ayoub.jpmcodingexercise.ui.viewmodels.PlanetViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: PlanetViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JpmCodingExerciseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold (
                        topBar = {
                            Text(text = "Planet Api")
                        }
                    ) { it ->
                        val planetPagingItems : LazyPagingItems<Planet> =
                            viewModel.planetsState.collectAsLazyPagingItems()
                        LazyColumn(
                            modifier = Modifier
                                .padding(it)
                                .padding(horizontal = 16.dp)
                        ) {

                            item { Spacer(modifier = Modifier.padding(4.dp)) }

                            items(planetPagingItems.itemCount) { index ->
                                planetPagingItems[index]?.let { planet ->
                                    Text(
                                        text = "Planet name : ${planet.name}",
                                        modifier = Modifier.padding(16.dp, 24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}