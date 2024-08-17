package com.example.e_ticaret.navigation

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.e_ticaret.R
import com.example.e_ticaret.presentation.product_screen.ProductScreen
import com.example.e_ticaret.presentation.shopping_cart_screen.ShoppingCartScreen


sealed class BottomNav(
    val route: String,
    val selected: Int,
    val unselected: Int,
    val content: String
) {
    object ProductBottom : BottomNav(
        "product_screen",
        R.drawable.home_full,
        R.drawable.home,
        "Anasayfa"
    )

    object CartBottom : BottomNav(
        "shopping_cart_screen",
        R.drawable.cart_full,
        R.drawable.cart,
        "Sepet"
    )
}

@Composable
fun AppBottomNav(navController: NavController) {
    val items = listOf(
        BottomNav.ProductBottom,
        BottomNav.CartBottom
    )
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        NavigationBar(
            modifier = Modifier.fillMaxWidth(),
            containerColor = Color.White
        ) {
            items.forEach { item ->
                val isSelected = currentRoute == item.route
                val icon = if (isSelected) item.selected else item.unselected
                val color = if (isSelected) Color.Black else Color.Gray
                val targetColor by animateColorAsState(targetValue = color)
                val targetFont = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold

                BottomNavigationItem(
                    selected = isSelected,
                    onClick = {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let {
                                popUpTo(route = it) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }

                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = icon),
                            contentDescription = item.content,
                            tint = targetColor,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.content,
                            fontSize = 9.sp,
                            color = targetColor,
                            fontWeight = targetFont
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun AppNavigation(navController : NavHostController) {

    NavHost(navController = navController, startDestination = Screen.ProductScreen.route ){
        composable(Screen.ProductScreen.route){
            ProductScreen()
        }
        composable(Screen.ShoppingCartScreen.route){
            ShoppingCartScreen()
        }
    }
}