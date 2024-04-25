package com.ayoub.jpmcodingexercise.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ayoub.jpmcodingexercise.ui.screen.PlanetDetailScreenRoute
import com.ayoub.jpmcodingexercise.ui.screen.PlanetsScreenRoute


object Destinations {
    object Planets {

        const val route = "planets"

        object PlanetDetails {
            const val key = "planetId"
            const val route = "${Planets.route}/{$key}"

            fun route(id: String) = "${Planets.route}/$id"
        }
    }
}


@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination : String = Destinations.Planets.route,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination,
    ) {

        composable(route = Destinations.Planets.route) { backStackEntry  ->
            PlanetsScreenRoute(
                viewModel = hiltViewModel(backStackEntry),
                onPlanetClick = { planetId ->
                    navController.navigate(Destinations.Planets.PlanetDetails.route(planetId))
                })
        }

        composable(
            route = Destinations.Planets.PlanetDetails.route,
            arguments = listOf(
                navArgument(Destinations.Planets.PlanetDetails.key) { type = NavType.StringType }
            )
        ) {  backStackEntry  ->
            PlanetDetailScreenRoute(
                viewModel = hiltViewModel(backStackEntry),
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }


    }
}