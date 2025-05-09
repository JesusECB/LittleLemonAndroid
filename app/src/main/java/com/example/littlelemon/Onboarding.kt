package com.example.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .height(150.dp)
                .width(250.dp)
                .padding(top = 16.dp),
            contentScale = ContentScale.Fit
        )

        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Color(0xFF495E57)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Let's get to know you",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Subtitle
        Text(
            text = "Personal information",
            fontSize = 18.sp,
            color = Color(0xFF333333)
        )

        // Input fields
        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email address") },
            modifier = Modifier.fillMaxWidth()
        )

        // Message
        if (message.isNotBlank()) {
            Text(
                text = message,
                color = if (message.contains("unsuccessful")) Color.Red else Color(0xFF495E57),
                fontSize = 14.sp
            )
        }

        // Register button
        Button(
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    message = "Registration unsuccessful. Please enter all data."
                } else {
                    if (!isPreview) {
                        val prefs = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
                        with(prefs.edit()) {
                            putString("firstName", firstName)
                            putString("lastName", lastName)
                            putString("email", email)
                            apply()
                        }
                        navController.navigate(HomeDestination.route)
                    }
                    message = "Registration successful!"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF4CE14),
                contentColor = Color.Black
            )
        ) {
            Text("Register")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnboardingPreview() {
    OnboardingScreen(navController = rememberNavController())
}
