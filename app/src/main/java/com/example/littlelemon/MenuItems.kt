package com.example.littlelemon

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage


@Composable
fun MenuItems(
    db: AppDatabase,
    searchPhrase: String,
    selectedCategory: String?
) {
    val menuItemsState = produceState<List<MenuItemEntity>?>(initialValue = null, db) {
        value = db.menuDao().getAll()
    }

    val menuItems = menuItemsState.value?.filter {
        val matchesSearch = searchPhrase.isBlank() || it.title.contains(searchPhrase, ignoreCase = true)
        val matchesCategory = selectedCategory.isNullOrBlank() || it.category.equals(selectedCategory, ignoreCase = true)
        matchesSearch && matchesCategory
    }

    Column(Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        menuItems?.forEach { item ->
            MenuItemRow(item)
            Divider()
        } ?: Text("Loading menu...", style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun MenuItemRow(item: MenuItemEntity) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = item.description, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "$${item.price}", style = MaterialTheme.typography.labelMedium)
        }

        AsyncImage(
            model = item.image,
            contentDescription = item.title,
            modifier = Modifier
                .size(80.dp)
                .padding(start = 8.dp),
            contentScale = ContentScale.Crop
        )
    }
}
