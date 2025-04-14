package com.example.newz.presentation.navigation.Routes

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newz.presentation.screens.CategoryDescriptionUI
import com.example.newz.presentation.screens.HomeScreenUI

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeScreenUI(navController = navController)
        }
        composable<CategoryScreen> {
            CategoryDescriptionUI( navController = navController)
        }

    }
}