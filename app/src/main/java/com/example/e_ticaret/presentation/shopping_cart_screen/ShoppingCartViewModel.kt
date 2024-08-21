package com.example.e_ticaret.presentation.shopping_cart_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_ticaret.data.usecase.AllProductCartListUseCase
import com.example.e_ticaret.data.usecase.DeleteProductUseCase
import com.example.e_ticaret.data.usecase.GetProductByNameUseCase
import com.example.e_ticaret.data.usecase.UpdateProductQuantityUseCase
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import com.example.e_ticaret.domain.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val getProductByNameUseCase: GetProductByNameUseCase,
    private val updateProductQuantityUseCase: UpdateProductQuantityUseCase,
    private val allProductCartListUseCase: AllProductCartListUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) : ViewModel() {

    private val _cartResponse = MutableStateFlow(CartState())
    val cartResponse: StateFlow<CartState> = _cartResponse.asStateFlow()

    private val _cartState = MutableStateFlow(CartState())
    val cartState: StateFlow<CartState> = _cartState.asStateFlow()
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
        try {
            val products = allProductCartListUseCase.invoke()
            _cartResponse.value = CartState(data = products)
        } catch (e: Exception) {
            _cartResponse.value = CartState(isError = e.message)
        }
    }

    fun increaseProductQuantity(product: ProductResponseItemDb) {
        viewModelScope.launch {
            try {
                val existingProduct = getProductByNameUseCase.invoke(product.name)
                if (existingProduct != null) {
                    val newQuantity = existingProduct.quantity!! + 1
                    updateProductQuantityUseCase.invoke(product.name, newQuantity)
                    loadProducts()
                }
            } catch (e: Exception) {
                _cartResponse.value = CartState(isError = e.message)
            }
        }
    }

    fun decreaseProductQuantity(product: ProductResponseItemDb) {
        viewModelScope.launch {
            try {
                val existingProduct = getProductByNameUseCase.invoke(product.name)
                if (existingProduct != null && existingProduct.quantity!! > 1) {
                    val newQuantity = existingProduct.quantity!! - 1
                    updateProductQuantityUseCase.invoke(product.name, newQuantity)
                    loadProducts()
                }
            } catch (e: Exception) {
                _cartResponse.value = CartState(isError = e.message)
            }
        }
    }



    fun deleteProduct(productResponseItemDb: ProductResponseItemDb) {
        viewModelScope.launch {
            try {
                deleteProductUseCase(productResponseItemDb)
                loadProducts()
                // UI güncelleme işlemleri
                Log.d("CartViewModel", "Product deleted successfully")
            } catch (e: Exception) {
                // Hata yönetimi
                Log.e("CartViewModel", "Error deleting product: ${e.message}")
            }
        }
    }
}

data class CartState(
    val data: List<ProductResponseItemDb>? = null,
    val isError: String? = null
)
