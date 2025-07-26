package com.example.smarthome_app.ui.tiles.shoppingTile

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShoppingScreen(viewModel: ShoppingViewModel = viewModel()) {
    LazyRow {
        items(viewModel.productList) { product ->
            Text(text = product.name)
        }
    }
}