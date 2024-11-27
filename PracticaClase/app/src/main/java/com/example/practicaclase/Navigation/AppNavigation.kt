package com.example.navegacion.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.practicaclase.Screens.Datos
import com.example.practicaclase.Screens.RespuestaScreen


@Composable
fun AppNavigation(modifier: Modifier) {
    val navControlador = rememberNavController()
    NavHost(navController = navControlador, startDestination = AppScreen.FirstScreen.route) {
        composable(AppScreen.FirstScreen.route) {
            Datos(modifier, navControlador)
        }
        composable(
            AppScreen.SecondScreen.route + "/{Nombre}/{Apellidos}/{Edad}/{Dni}",
            arguments = listOf(
                navArgument(name = "Nombre") {
                    type = NavType.StringType
                },
                navArgument(name = "Apellidos") {
                    type = NavType.StringType
                },
                navArgument(name = "Edad") {
                    type = NavType.IntType
                },
                navArgument(name = "Dni") {
                    type = NavType.StringType
                })
        ) {
            RespuestaScreen(
                modifier,
                navControlador,
                it.arguments?.getString("Nombre"),
                it.arguments?.getString("Apellidos"),
                it.arguments?.getInt("Edad"),
                it.arguments?.getString("Dni")
            )
        }

    }
}