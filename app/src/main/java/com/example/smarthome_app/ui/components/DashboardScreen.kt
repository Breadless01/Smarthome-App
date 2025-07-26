package com.example.smarthome_app.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.smarthome_app.model.TileData
import com.example.smarthome_app.ui.tiles.shoppingTile.ShoppingTile
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController

@Composable
fun DashboardScreen(navController: NavHostController) {
    val tablet = isTablet()
    val tiles = remember { mutableStateListOf(
        TileData("\uD83D\uDED2 Einkaufsliste", 0, 0, 2, 2) {
            ShoppingTile()
        }
    ) }

    if (isTablet()) {
        GridCanvas(
            tileList = tiles,
        )
    } else {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp, 32.dp)
                .fillMaxWidth()
        ) {
            items(tiles) { tile ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { navController.navigate("shopping") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(horizontal = 12.dp, vertical = 20.dp)
                    ) { Text(
                        text = tile.id,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodyLarge
                    ) }
                }
            }
        }
    }

}

