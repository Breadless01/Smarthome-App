package com.example.smarthome_app.model

import androidx.compose.runtime.Composable

data class TileData(
    val id: String,
    val posX: Int,
    val posY: Int,
    val width: Int,
    val height: Int,
    val content: @Composable () -> Unit
)
