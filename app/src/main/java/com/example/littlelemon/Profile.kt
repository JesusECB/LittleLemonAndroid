package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)

    val firstName = sharedPreferences.getString("firstName", "") ?: ""
    val lastName = sharedPreferences.getString("lastName", "") ?: ""
    val email = sharedPreferences.getString("email", "") ?: ""

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
        Text(
            text = "Profile",
            style = MaterialTheme.typography.titleLarge
        )

        Text("First Name: $firstName", fontSize = 18.sp)
        Text("Last Name: $lastName", fontSize = 18.sp)
        Text("Email: $email", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                sharedPreferences.edit().clear().apply()
                navController.navigate(OnboardingDestination.route) {
                    popUpTo(0)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF495E57),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
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
