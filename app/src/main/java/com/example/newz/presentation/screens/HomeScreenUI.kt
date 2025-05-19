package com.example.newz.presentation.screens

import android.R.attr.color
import android.R.attr.contentDescription
import android.R.attr.enabled
import android.R.attr.onClick
import android.R.attr.text
import android.R.id.bold
import android.graphics.Color.red
import android.graphics.drawable.shapes.OvalShape
import android.widget.Space
import com.example.newz.R  // manually added import for AsyncImage(placeholder = painterResource(R.drawable.ic_launcher_foreground))
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ChipColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black

import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.ExtraBold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.colorSpace
import com.example.newz.presentation.NewzViewModel.NewzViewModel


import com.example.newz.presentation.navigation.Routes.CategoryScreen
import com.example.newz.ui.theme.NewzTheme
import com.example.newz.ui.theme.Pink40
import com.example.newz.ui.theme.Pink80
import com.example.newz.ui.theme.Purple40
import com.example.newz.ui.theme.Purple80
import kotlinx.serialization.Serializable

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenUI(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: NewzViewModel
) {
    val searchTerm = rememberSaveable { mutableStateOf("") }
    val state = viewModel.state.collectAsState()
    val categoryToSearch = listOf(
        "Latests",
        "Premanand\n  Maharaj Ji",
        "India",
        "Pahalgham",
        "Technology",
        "Business",
        "Bollywood",
        "Hollywood",
        "Tollywood"
    )
    val scrollState = rememberLazyListState() // this is for retaining the lazy row scroll state
    val scrollState1 =  rememberSaveable(saver = LazyListState.Saver) { // this is for retaining the lazy Column scroll state
        LazyListState()
    }
    var selectedCategory by rememberSaveable { mutableStateOf("") }
    if (state.value.loading == true) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(25.dp),
                color = Yellow // for circular progress indicator
            )
            /* LinearProgressIndicator(
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(4.dp),
                   color = Purple80, Purple40
             )
             // if want linear progress indicator
 */
        }
    } else if (state.value.error.isNullOrBlank().not()) {
        Text(text = state.value.error.toString())
    } else {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchTerm.value,
                    onValueChange = {
                        searchTerm.value = it
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(30.dp),
                    placeholder = {
                        Text(
                            text = "Type to search latest headlines",
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    label = { Text(text = "Search News!", color = Gray) },
                    colors = outlinedTextFieldColors(
                        focusedBorderColor = Transparent,
                        unfocusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedTextColor = MaterialTheme.colorScheme.secondary,
                        cursorColor = Yellow
                    ),
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                viewModel.getEverything(searchTerm.value)
                            },
                            enabled = searchTerm.value.isNullOrBlank().not()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Icon",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth(), state = scrollState
            ) {
                items(categoryToSearch) {
                    Card(
                        onClick = {
                            viewModel.getEverything(q = it)
                            selectedCategory = it
                        },
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .padding(horizontal = 2.dp)
                            .width(100.dp)
                            .height(50.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .border(
                                    color = MaterialTheme.colorScheme.primary,
                                    width = 1.dp,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .background(
                                    color =
                                        if (it.equals(selectedCategory)) {
                                            MaterialTheme.colorScheme.tertiary
                                        } else {
                                            MaterialTheme.colorScheme.primary
                                        }
                                ),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = it, color = MaterialTheme.colorScheme.secondary, fontWeight =
                                    if (it.equals(selectedCategory)) {
                                        Bold
                                    } else {
                                        Normal
                                    }
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            val data = state.value.data
            if (data?.articles!!.isEmpty()) {
                Text(text = "No Data Found")
            } else {
                val articles = data.articles.filter { article ->
                    article.title?.contains("Removed") == false
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 0.dp, 0.dp, 0.dp),
                    state = scrollState1
                ) {
                    items(articles) { article ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(16.dp)
                                .clickable {
                                    navController.navigate(
                                        CategoryScreen(
                                            author = article.author ?: "Unknown",
                                            content = article.content
                                                ?: "No content provided from the host",
                                            description = article.description
                                                ?: "No Description provided by the host",
                                            publishedAt = article.publishedAt ?: "Not Known",
                                            id = article.source!!.id ?: "Not Known",
                                            name = article.source.name ?: "Unknown",
                                            title = article.title ?: "",
                                            url = article.url ?: "",
                                            urlToImage = article.urlToImage ?: ""
                                        )
                                    )
                                }
                        ) {
                            Column(Modifier.fillMaxSize()) {
                                article.publishedAt?.let { Text("Publishing date  : ${it.toString()} ") }
                                if (article.urlToImage.isNullOrBlank()) {
                                    Image(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .width(20.dp),
                                        painter = painterResource(R.drawable.ic_launcher_foreground),
                                        contentDescription = null
                                    )
                                    Text("Failed to load image")
                                } else {
                                    SubcomposeAsyncImage(
                                        model = article.urlToImage,
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        loading = {
                                            LinearProgressIndicator(
                                                modifier = Modifier.size(2.dp),
                                                color = Yellow
                                            )
                                                  },
                                        error = {
                                            Text("Failed to load image")
                                        },
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clickable {
                                                navController.navigate(
                                                    CategoryScreen(
                                                        author = article.author ?: "Unknown",
                                                        content = article.content
                                                            ?: "No content available",
                                                        description = article.description
                                                            ?: "No description available",
                                                        publishedAt = article.publishedAt
                                                            ?: "Unknown date",
                                                        id = article.source?.id ?: "Unknown source",
                                                        name = article.source?.name
                                                            ?: "Unknown source",
                                                        title = article.title ?: "No title",
                                                        url = article.url ?: "",
                                                        urlToImage = article.urlToImage ?: ""
                                                    )
                                                )
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
}
