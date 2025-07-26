package com.example.smarthome_app.ui.tiles.shoppingTile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smarthome_app.model.ShoppingItem
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class ShoppingViewModel : ViewModel() {
    var productList by mutableStateOf(listOf<ShoppingItem>())
        private set

    fun addProduct(product: String, fullQuantity: String) {
        if (product.isNotBlank()) {
            productList = productList + ShoppingItem(product, fullQuantity)
            sendToServer(product)
        }
    }

    fun sendToPhone() {
        viewModelScope.launch(Dispatchers.IO) {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json()
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response: HttpResponse = client.post("http://10.0.2.2:8080/sendShoppingList") {
                        setBody(Json.encodeToString(productList))
                    }
                    println(Json.encodeToString(productList))
                } catch (e: Exception) {
                    println("Fehler beim Senden der Anfrage: ${e.message}")
                } finally {
                    client.close()
                }
            }
        }
    }

    fun sendToServer(product: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val client = HttpClient(CIO) {
                install(ContentNegotiation) {
                    json()
                }
            }
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response: HttpResponse = client.post("http://10.0.2.2:8080/addProducts") {
                        contentType(ContentType.Application.Json)
                        setBody(mapOf("product" to product))
                    }
                    val body: String = response.body()
                    println("Antwort vom Server: $body")
                } catch (e: Exception) {
                    println("Fehler beim Senden der Anfrage: ${e.message}")
                } finally {
                    client.close()
                }
            }
        }

    }


}
