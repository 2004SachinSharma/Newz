package com.example.newz.presentation.navigation.Routes

import com.example.newz.data.model.Source
import kotlinx.serialization.Serializable

// Here are two routes , one for home screen and another for category screen
// HomeScreen is used for the home screen and CategoryScreen is used for the category screen

@Serializable
object HomeScreen // HomeScreen is declared in this way , because I don't have to pass any data to the HomeScreen, but if I have to pass data to the HomeScreen , then I have to declare it in the way , how category screen is declared
@Serializable
data class CategoryScreen( val author: String? = null,  // CategoryScreen is declared in this way , because I have to pass data to the CategoryScreen
                           val content: String? = null,
                           val description: String? = null,
                           val publishedAt: String? = null,
                           val id: String? = null,
                           val name: String? = null ,
                           val title: String? = null,
                           val url: String? = null,
                           val urlToImage: String? = null
)

// The point to take is when I have to pass data to any routes then I would have to declare it as a data class , and when I don't have to pass data to any routes then I would have to declare it as an object.


/*
@Serializable
open class NavigationRoutes {
    object HomeScreen : NavigationRoutes()
    object CategoryScreen : NavigationRoutes()

}*/ // it have important use case in navigation , but using else way in this project for the same so commented out
