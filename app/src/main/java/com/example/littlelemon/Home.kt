package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color


@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(75.dp)
                    .clickable {
                        navController.navigate(ProfileDestination.route)
                    }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .width(250.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            HeroSection()
        }
    }
}

@Composable
fun HeroSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = androidx.compose.ui.graphics.Color(0xFF495E57))
            .padding(16.dp)
    ) {
        Text(
            text = "Little Lemon",
            style = MaterialTheme.typography.headlineMedium,
            color = androidx.compose.ui.graphics.Color(0xFFF4CE14)
        )
        Text(
            text = "Chicago",
            style = MaterialTheme.typography.titleMedium,
            color = androidx.compose.ui.graphics.Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
            style = MaterialTheme.typography.bodyMedium,
            color = androidx.compose.ui.graphics.Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Image(
            painter = painterResource(id = R.drawable.hero_image), // Asegúrate de tener este asset
            contentDescription = "Hero Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Surface {
        HomeScreen(navController = rememberNavController())
    }
}
