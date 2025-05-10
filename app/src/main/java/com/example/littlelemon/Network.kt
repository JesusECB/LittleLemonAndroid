package com.example.littlelemon

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import io.ktor.serialization.kotlinx.json.*
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
)

// ✅ Cliente global de Ktor configurado
val client = HttpClient(Android) {
    install(ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
        })
    }
}

// ✅ Función para hacer la petición
suspend fun fetchMenu(): MenuNetwork {
    val response: HttpResponse = client.get("https://raw.githubusercontent.com/JesusECB/Working-With-Data-API/main/menu.json")
    val rawJson = response.bodyAsText()

    return Json { ignoreUnknownKeys = true }.decodeFromString(rawJson)
}
