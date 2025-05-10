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

                    val db = AppDatabase.getDatabase(applicationContext)
                    val dao = db.menuDao()

                    val entities = menu.menu.map {
                        MenuItemEntity(
                            id = it.id,
                            title = it.title,
                            description = it.description,
                            price = it.price,
                            image = it.image,
                            category = it.category
                        )
                    }

                    withContext(Dispatchers.IO) {
                        dao.insertAll(entities)
                    }

                    Log.d("NETWORK", "Saved ${entities.size} items to DB.")
                } catch (e: Exception) {
                    Log.e("NETWORK", "Error fetching or saving menu: ${e.message}")
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
