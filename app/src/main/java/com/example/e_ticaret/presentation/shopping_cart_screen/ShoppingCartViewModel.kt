package com.example.e_ticaret.presentation.shopping_cart_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import com.example.e_ticaret.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartResponse = mutableStateOf(CartState())
    val cartResponse: State<CartState> = _cartResponse

    init {
        loadProducts()
    }

    fun addProductToCart(product: ProductResponseItemDb) {
        viewModelScope.launch {
            cartRepository.addOrUpdateProduct(product)
            loadProducts()
        }
    }

    private fun loadProducts() = viewModelScope.launch {
        _cartResponse.value = CartState(
            data = cartRepository.allProductDb()
        )
    }

    fun increaseProductQuantity(product: ProductResponseItemDb) {
        viewModelScope.launch {
            val existingProduct = cartRepository.getProductByName(product.name)
            if (existingProduct != null) {
                val newQuantity = existingProduct.quantity!! + 1
                cartRepository.updateProductQuantity(product.name, newQuantity)
                loadProducts()
            }
        }
    }

    fun decreaseProductQuantity(product: ProductResponseItemDb) {
        viewModelScope.launch {
            val existingProduct = cartRepository.getProductByName(product.name)
            if (existingProduct != null && existingProduct.quantity!! > 1) {
                val newQuantity = existingProduct.quantity!! - 1
                cartRepository.updateProductQuantity(product.name, newQuantity)
                loadProducts()
            }
        }
    }
}

data class CartState(
    val data: List<ProductResponseItemDb>? = null,
    val isError: String? = ""
)
