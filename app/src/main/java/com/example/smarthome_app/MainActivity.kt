package com.example.smarthome_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.smarthome_app.network.KtorClientService
import com.example.smarthome_app.ui.components.DashboardScreen
import com.example.smarthome_app.ui.components.Navigation
import com.example.smarthome_app.ui.theme.Smarthome_AppTheme
import com.example.smarthome_app.ui.tiles.shoppingTile.ShoppingTile
import com.example.smarthome_app.ui.tiles.shoppingTile.ShoppingViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        KtorClientService.checkServerStatus()

        enableEdgeToEdge()
        setContent {
            Smarthome_AppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation(navController = rememberNavController())
                }
            }
        }
    }
}