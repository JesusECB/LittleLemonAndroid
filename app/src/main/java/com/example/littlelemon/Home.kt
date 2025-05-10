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
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Little Lemon Logo",
                modifier = Modifier
                    .width(250.dp)
                    .height(100.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text("Welcome to the Home screen!", style = MaterialTheme.typography.titleLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Surface {
        HomeScreen(navController = rememberNavController())
    }
}
