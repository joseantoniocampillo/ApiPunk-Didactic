package dev.campi.apipunksolution.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.campi.apipunksolution.navigation.SetupNavGraph
import dev.campi.apipunksolution.ui.theme.ApiPunkSolutionTheme

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ApiPunkSolutionTheme {
                navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}