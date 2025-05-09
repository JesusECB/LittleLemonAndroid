package com.example.littlelemon

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the Home screen!",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                navController.navigate(ProfileDestination.route)
            }
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
