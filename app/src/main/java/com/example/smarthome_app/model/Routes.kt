package com.example.smarthome_app.model

enum class Screen {
    HOME,
    SHOPPING,
}

sealed class Routes (val route: String) {
    object Home : Routes("home")
    object Shopping : Routes("shopping")
}