package com.example.newz.presentation.navigation.Routes

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newz.data.model.Article
import com.example.newz.data.model.Source
import com.example.newz.presentation.NewzViewModel.NewzViewModel
import com.example.newz.presentation.screens.CategoryDescriptionUI
import com.example.newz.presentation.screens.HomeScreenUI
import kotlinx.serialization.Serializable


@Composable
fun AppNavigation(modifier: Modifier = Modifier, viewModel: NewzViewModel) {

    val navController = rememberNavController()

    NavHost(
        navController = navController, startDestination = HomeScreen
    ) {
        composable<HomeScreen> {
            HomeScreenUI(modifier = modifier , navController = navController, viewModel = viewModel)
        }

        composable<CategoryScreen> {
            val categoryScreen = it.toRoute<CategoryScreen>()
            var article = Article(
                author = categoryScreen.author,
                content = categoryScreen.content,
                description = categoryScreen.description,
                publishedAt = categoryScreen.publishedAt,
                source = Source(
                    id = categoryScreen.id,
                    name = categoryScreen.name
                ),
                title = categoryScreen.title,
                url = categoryScreen.url,
                urlToImage = categoryScreen.urlToImage
            )
            CategoryDescriptionUI(article = article, navController = navController)
        }

        composable("category_Screen") {

        }

    }
}
