package com.example.newz.presentation.screens

import android.R.attr.contentDescription
import android.R.attr.text
import android.graphics.Color.red
import android.graphics.drawable.shapes.OvalShape
import com.example.newz.R  // manually added import for AsyncImage(placeholder = painterResource(R.drawable.ic_launcher_foreground))
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.newz.presentation.NewzViewModel.NewzViewModel

import com.example.newz.presentation.navigation.Routes.CategoryScreen

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NewzViewModel
) {
    val searchTerm = remember { mutableStateOf("") }
    val state = viewModel.state.collectAsState()
    if (state.value.loading == true) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            CircularProgressIndicator()
        }
    } else if (state.value.error.isNullOrBlank().not()) {
        Text(text = state.value.error.toString())
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Row {
                OutlinedTextField(
                    modifier = Modifier.width(  390.dp),
                    value = searchTerm.value,
                    onValueChange = {
                        searchTerm.value = it
                    },
                    shape = CircleShape,
                    placeholder = { Text(text = "Search") },
                )
                IconButton(onClick = {
                    viewModel.getHeadLines(searchTerm.value)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
                IconButton(onClick = {
                    viewModel.getHeadLines(searchTerm.value)
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }
            }


            val data = state.value.data
            if (data?.articles!!.isEmpty()) {
                Text(text = "No Data Found")
            } else {
                val articles = data.articles
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 60.dp, 0.dp, 0.dp)
                ) {
                    items(articles) { article ->

                        Card() {
                            Column() {

                                AsyncImage(
                                    model = article.urlToImage,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clickable {
                                            navController.navigate(CategoryScreen)
                                        }
                                )
                                article.title?.let { Text(text = it) }

                            }


                        }

                    }
                }
            }
        }
    }
}
