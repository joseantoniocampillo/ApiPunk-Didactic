package dev.campi.apipunksolution.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object Other : Screen("other_screen")
}
