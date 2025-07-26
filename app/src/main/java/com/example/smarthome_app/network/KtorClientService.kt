package com.example.smarthome_app.network

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object KtorClientService {

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    fun checkServerStatus() {
        println("making request to go server")
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: String = client.get("http://10.0.2.2:8080/").body()
                Log.d("Server", "Antwort: $response")
            } catch (e: Exception) {
                Log.e("Server", "Fehler beim Abrufen: ${e.message}")
            }
        }
    }

    fun addProduct(text: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response: HttpResponse = client.post("http://10.0.2.2:8080/addProducts") {
                    contentType(ContentType.Application.Json)
                    setBody(mapOf("name" to text))
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
