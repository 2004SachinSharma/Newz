package com.example.newz.presentation.screens

// Required Compose and AndroidX imports
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.newz.R
import com.example.newz.data.model.Article
import com.example.newz.data.model.Source

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDescriptionUI(
    modifier: Modifier = Modifier,
    navController: NavController, // NavController for navigating back
    article: Article              // Article object to display details
) {
    // Scaffold provides a standard layout structure: TopAppBar + content
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Newz") }, // App title in TopBar

                // Back button using AutoMirrored ArrowBack icon (auto flips in RTL)
                navigationIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .clickable { navController.popBackStack() } // Navigate back on click
                    )
                }
            )
        }
    ) { paddingValues ->
        // Main content container
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)              // Respect Scaffold padding
                    .padding(top = 30.dp)                // Space below TopAppBar
                    .verticalScroll(rememberScrollState()) // Enables scrolling for smaller screens
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(20.dp),                     // Inner content padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(24.dp)) // Vertical space

                // Article Title
                Text(
                    text = article.title ?: "No Title",
                    fontStyle = FontStyle.Italic,
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                // News Image using Coil's AsyncImage
                AsyncImage(
                    model = article.urlToImage,
                    contentDescription = null,
                    modifier = Modifier.size(300.dp), // Fixed image size
                    placeholder = painterResource(R.drawable.ic_launcher_foreground) // Fallback image
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Author and Publish Date
                Text(color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = Color(0xFFF10F5D), // Fixed color hex (previous had extra digit)
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp)
                        ,
                    text = "Author: ${article.author ?: "Unknown"}\nPublish date: ${article.publishedAt ?: "Unknown"}",
                    style = TextStyle(fontSize = 16.sp, color = Color.White)
                )
            }
        }
    }
}

// Preview Composable for UI testing in Android Studio
@Preview(showSystemUi = true)
@Composable
fun PreviewCategoryDescriptionUI() {
    // Fake article for preview
    CategoryDescriptionUI(
        navController = rememberNavController(), // Dummy NavController
        article = Article(
            source = Source(
                id = "null",
                name = "Financial Times"
            ),
            author = "Claire Jones",
            title = "Republican senator backs Powell over Trump attacks on Fed - Financial Times",
            description = "Banking committee member John Kennedy says US central bank ‘ought to be independent’",
            url = "https://www.ft.com/content/8fc4279c-7098-48d9-b21d-be24a42c1ae4",
            urlToImage = "https://www.ft.com/__origami/service/image/v2/images/raw/https%3A%2F%2Fd1e00ek4ebabms.cloudfront.net%2Fproduction%2Fd3904513-49d0-4c67-90f0-dced81945a33.jpg?source=next-barrier-page",
            content = "White House Watch newsletter...",
            publishedAt = "23 April 2025"
        )
    )
}
