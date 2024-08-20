package com.example.e_ticaret.presentation.shopping_cart_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_ticaret.presentation.component.ShoppingCartComponent

@Composable
fun ShoppingCartScreen(
    cartViewModel: ShoppingCartViewModel = hiltViewModel()
) {
    val state = cartViewModel.cartResponse.value

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        state.data?.let {
            LazyColumn {
                items(state.data){
                    ShoppingCartComponent(it,
                        onIncreaseQuantity = { cartViewModel.increaseProductQuantity(it) },
                        onDecreaseQuantity = { cartViewModel.decreaseProductQuantity(it) })
                }

            }
        }
    }

}