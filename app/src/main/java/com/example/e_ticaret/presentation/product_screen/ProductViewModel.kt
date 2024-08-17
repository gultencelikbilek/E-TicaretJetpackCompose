package com.example.e_ticaret.presentation.product_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_ticaret.data.network.NetworkResult
import com.example.e_ticaret.data.usecase.AddProductUseCase
import com.example.e_ticaret.data.usecase.AllProductUseCase
import com.example.e_ticaret.domain.model.ProductResponseItem
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val allProductUseCase: AllProductUseCase,
    private val addProductUseCase: AddProductUseCase
) : ViewModel() {

    private val _productState = mutableStateOf(ProductState())
    val productState: State<ProductState> = _productState
    init {
        allProduct()
    }

    fun allProduct() = viewModelScope.launch {
        allProductUseCase.invoke().collect { result ->
            when (result) {
                is NetworkResult.Error -> {
                    _productState.value = ProductState(
                        isError = result.errorMessage
                    )
                    Log.d("errorproductviewmodel:", result.errorMessage)
                }

                NetworkResult.Loading -> {
                    _productState.value = ProductState(
                        isLoading = true
                    )
                }

                is NetworkResult.Succes -> {
                    _productState.value = ProductState(
                        data = result
                    )
                    Log.d("succesproductviewmodel:", result.toString())
                }
            }
        }
    }


    fun addProductDb(productResponseItemDb: ProductResponseItemDb) = viewModelScope.launch {
        addProductUseCase.invoke(productResponseItemDb)
    }
}

data class ProductDbState(
    val data : ProductResponseItem? = null,
    val isError : String? ="",
    val isLoading : Boolean? = false
)

data class ProductState(
    val data: NetworkResult<List<ProductResponseItem>>? = null,
    val isError: String? = "",
    val isLoading: Boolean? = false
)