package com.example.e_ticaret.navigation

sealed class Screen(val route : String) {
    object ProductScreen : Screen("product_screen")
    object ShoppingCartScreen : Screen("shopping_cart_screen")
}