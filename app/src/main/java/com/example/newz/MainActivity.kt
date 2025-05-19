package com.example.newz

import android.R.attr.shape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newz.presentation.NewzViewModel.NewzViewModel
import com.example.newz.presentation.navigation.Routes.AppNavigation
import com.example.newz.ui.theme.NewzTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewmodel by viewModels<NewzViewModel>()
            NewzTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        // 🔝 Top Title
                        Text(
                            text = "The Newz App",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(16.dp),
                            color = MaterialTheme.colorScheme.primary // Beige
                            )

                        // 🔝 Top Divider
                        HorizontalDivider(color = Yellow, thickness = 0.2.dp, modifier = Modifier)

                        // 🔁 Scrollable/Content Area
                        Column(
                            modifier = Modifier
                                .weight(1f) // Takes up remaining space between top and bottom
                                .fillMaxWidth()
                        ) {
                            AppNavigation(viewModel = viewModel())
                        }

                        // 🔻 Bottom Divider (just above bottom bar/footer)
                        HorizontalDivider(color = Yellow, thickness = 2.dp)

                        // 🔻 Bottom footer content
                        Text(
                            text = "The Newz App",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        NewzTheme {
            Box(contentAlignment = Alignment.Center) {
                Text("Preview")
            }
        }
    }
}
