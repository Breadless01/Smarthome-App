package com.example.smarthome_app.model

import kotlinx.serialization.Serializable

@Serializable
data class ShoppingItem(
    val name: String,
    val quantity: String
)
