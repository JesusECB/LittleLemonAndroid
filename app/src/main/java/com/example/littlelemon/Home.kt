//Home.kt
package com.example.littlelemon

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val searchPhrase = remember { mutableStateOf("") }
    val selectedCategory = remember { mutableStateOf("") }

    val db = AppDatabase.getDatabase(LocalContext.current)

    val menuItemsState = produceState<List<MenuItemEntity>?>(initialValue = null, db) {
        value = db.menuDao().getAll()
    }
    val menuItems = menuItemsState.value
    val categories = listOf("starters", "mains", "desserts", "drinks")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        navController.navigate(ProfileDestination.route)
                    }
            )
        }

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .width(250.dp)
                .height(40.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(14.dp))

        HeroSection(searchPhrase)

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "ORDER FOR DELIVERY!",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                val isSelected = category == selectedCategory.value
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        selectedCategory.value = if (isSelected) "" else category
                    },
                    label = {
                        Text(
                            text = category.replaceFirstChar { it.uppercaseChar() },
                            color = Color(0xFF333333)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = Color(0xFFEDEFEE)
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        MenuItems(
            db = db,
            searchPhrase = searchPhrase.value,
            selectedCategory = selectedCategory.value.ifBlank { null }
        )
    }
}

@Composable
fun HeroSection(searchPhrase: MutableState<String>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF495E57))
            .padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            ) {
                Text(
                    text = "Little Lemon",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFF4CE14)
                )
                Text(
                    text = "Chicago",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White
                )
            }

            Image(
                painter = painterResource(id = R.drawable.hero_image),
                contentDescription = "Hero Image",
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = searchPhrase.value,
            onValueChange = { searchPhrase.value = it },
            placeholder = { Text("Enter search phrase") },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
    }
}
