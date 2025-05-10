package com.example.littlelemon

import android.os.Bundle
import android.content.Context
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val sharedPreferences = getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
            val isLoggedIn = sharedPreferences.getString("firstName", null) != null

            val startDestination = if (isLoggedIn) {
                HomeDestination.route
            } else {
                OnboardingDestination.route
            }

            // ✅ Nuevo fetch limpio
            LaunchedEffect(Unit) {
                try {
                    val menu = withContext(Dispatchers.IO) { fetchMenu() }
                    Log.d("NETWORK", "Fetched ${menu.menu.size} items")
                } catch (e: Exception) {
                    Log.e("NETWORK", "Error fetching menu: ${e.message}")
                }
            }

            LittleLemonTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationComposable(
                        navController = navController,
                        startDestination = startDestination
                    )
                }
            }
        }
    }
}
