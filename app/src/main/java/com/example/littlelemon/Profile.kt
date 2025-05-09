package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "User Profile",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                // Clear user data
                val prefs = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
                prefs.edit().clear().apply()

                // Navigate back to Onboarding
                navController.navigate(OnboardingDestination.route) {
                    popUpTo(0) // Elimina el back stack para no volver atrás con el botón
                }
            }
        ) {
            Text("Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    ProfileScreen(navController = rememberNavController())
}
