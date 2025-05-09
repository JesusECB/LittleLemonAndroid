package com.example.littlelemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.littlelemon.ui.theme.LittleLemonTheme
import androidx.navigation.compose.rememberNavController
import android.content.Context


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LittleLemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    val sharedPreferences = getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
                    val isLoggedIn = sharedPreferences.getString("firstName", null) != null

                    val startDestination = if (isLoggedIn) {
                        OnboardingDestination.route
                    } else {
                        HomeDestination.route
                    }

                    NavigationComposable(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
