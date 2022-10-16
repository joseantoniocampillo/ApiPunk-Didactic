package dev.campi.apipunksolution.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.campi.apipunksolution.presentation.screens.main.HomeScreen
import dev.campi.apipunksolution.presentation.screens.main.MainViewModel

/**
 * Finalmente en la implementación didáctica del proyecto dejamos el esquema de una navegación de pantallas
 * mediante ir pasando el navController que generamos en la actividad, aunuqe finalmente no hacemos uso de
 * él en esta prueba concepto.
 * Dejo una clase a la sin uso a la que se navegaría tomando el navController: OtherScreen
 */

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen()
        }
        composable(route = Screen.Other.route) {
            OtherScreen(navController)
        }
    }
}

@Composable
fun OtherScreen(
    navController: NavHostController,
    otherViewModel: MainViewModel = hiltViewModel() // aquí pasariamos su propio viewmodel
) {
    TODO("No tiene caso de uso en este proyecto queda a modo de ejemplo de navegación")
}
