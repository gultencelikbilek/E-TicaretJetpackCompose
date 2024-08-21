package com.example.e_ticaret.presentation.shopping_cart_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_ticaret.presentation.component.ShoppingCartComponent

@Composable
fun ShoppingCartScreen(
    cartViewModel: ShoppingCartViewModel = hiltViewModel()
) {
    val state by cartViewModel.cartResponse.collectAsState()



    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = {padding ->
            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
                    .padding(padding)
            ) {
                items(
                    state.data ?: emptyList(),
                    // key = { it.id }
                ) { product ->
                    ShoppingCartComponent(
                        product,
                        onIncreaseQuantity = { cartViewModel.increaseProductQuantity(product) },
                        onDecreaseQuantity = { cartViewModel.decreaseProductQuantity(product) },
                        onDeleteProduct = { cartViewModel.deleteProduct(product) }
                    )
                }
            }
        }
    )

}
