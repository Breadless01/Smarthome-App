package com.example.smarthome_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.smarthome_app.model.TileData

@Composable
fun GridCanvas(
    tileList: List<TileData>,
    columns: Int = 6,
    rows: Int = 4
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
            .fillMaxSize()
            .padding(20.dp)
    ) {
        val cellWidth = (maxWidth - (columns - 1) * 20.dp) / columns
        val cellHeight = (maxHeight - (rows - 1) * 20.dp) / rows

        tileList.forEach { tile ->
            val offsetX = (cellWidth + 20.dp) * tile.posX
            val offsetY = (cellHeight + 20.dp) * tile.posY

            Box(
                modifier = Modifier
                    .offset(x = offsetX, y = offsetY)
                    .size(cellWidth * tile.width + 20.dp * (tile.width - 1), cellHeight * tile.height + 20.dp * (tile.height - 1))
                    .background(
                        color = Color(0xFF2C2C2C),
                        shape = MaterialTheme.shapes.medium
                    )
            ) {
                tile.content()
            }
        }
    }
}
