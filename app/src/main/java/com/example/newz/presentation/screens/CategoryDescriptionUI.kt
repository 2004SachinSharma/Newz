package com.example.newz.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.newz.presentation.navigation.Routes.HomeScreen

@Composable
fun CategoryDescriptionUI(modifier: Modifier = Modifier, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = " Category Description Screen", modifier = modifier.clickable {
            Color.Green
            navController.navigate(HomeScreen)
        }, color = Color.Blue)
    }
}

