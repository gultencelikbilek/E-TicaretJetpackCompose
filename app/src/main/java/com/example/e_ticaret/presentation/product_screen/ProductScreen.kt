package com.example.e_ticaret.presentation.product_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.e_ticaret.data.network.NetworkResult
import com.example.e_ticaret.presentation.component.CardComponent
import com.example.e_ticaret.presentation.component.Header

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProductScreen(
    productViewModel: ProductViewModel = hiltViewModel()
) {
    val state = productViewModel.productState.value

    when (state.data) {
        is NetworkResult.Error -> {
            Log.d("errorscreen:", state.isError.toString())
        }

        NetworkResult.Loading -> {
            CircularProgressIndicator()
        }

        is NetworkResult.Succes -> {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Header(text = "Tüm Ürünler")
                    })
                },
                content = {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.padding(it)
                    ) {
                        items(state.data.data) { product ->
                            CardComponent(product)
                        }
                        Log.d("successscreen:", state.data.data.toString())
                    }
                }
            )
        }

        null -> {
            Log.d("errorscreen:", "State data is null")
        }
    }
}