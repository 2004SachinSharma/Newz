package com.example.newz

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.SnapPosition.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newz.data.ApiBuilder.ApiBuilder.ApiBuilder
import com.example.newz.presentation.navigation.Routes.AppNavigation
import com.example.newz.presentation.screens.HomeScreenUI
import com.example.newz.ui.theme.NewzTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewzTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Column(modifier = Modifier.padding(it)) {

                        AppNavigation()


                    }
                }
                GlobalScope.launch {
                    Log.d("ApiResponse", "onCreate: ${ApiBuilder.retrofitObject().getHeadLines()}")
                }

            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        NewzTheme {
            Box(contentAlignment = Alignment.Center) {


            }
        }
    }
}
