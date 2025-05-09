package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "") ?: ""

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(100.dp)
                .width(200.dp)
        )

        // Welcome message
        Text(
            text = "Welcome, $firstName!",
            style = MaterialTheme.typography.titleLarge
        )

        // Profile navigation button
        Button(
            onClick = { navController.navigate(ProfileDestination.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF495E57),
                contentColor = Color.White
            )
        ) {
            Text("Go to Profile")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    HomeScreen(navController = rememberNavController())
}
