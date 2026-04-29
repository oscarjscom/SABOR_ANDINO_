package com.example.saborandino.navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Home : Screen("home")
    object Menu : Screen("menu")
    object Profile : Screen("profile")
    object Pedido : Screen("pedido")
    object Detail : Screen("detail/{platoId}") {
        fun createRoute(platoId: Int) = "detail/$platoId"
    }
}