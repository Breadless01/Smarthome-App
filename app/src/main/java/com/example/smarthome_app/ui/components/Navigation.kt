package com.example.smarthome_app.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smarthome_app.model.Routes
import com.example.smarthome_app.ui.tiles.shoppingTile.ShoppingScreen


@Composable
fun Navigation(
    navController: NavHostController,
    startDestination: String = Routes.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(Routes.Home.route) {
            DashboardScreen(navController)
        }
        composable(Routes.Shopping.route) {
            ShoppingScreen()
        }
    }
}
