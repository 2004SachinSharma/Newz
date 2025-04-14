package com.example.newz.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

import com.example.newz.presentation.navigation.Routes.CategoryScreen

@Composable
fun HomeScreenUI(modifier  : Modifier = Modifier, navController : NavController ) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Home Screen", modifier = modifier.clickable {
            navController.navigate(CategoryScreen)
        })
    }
}