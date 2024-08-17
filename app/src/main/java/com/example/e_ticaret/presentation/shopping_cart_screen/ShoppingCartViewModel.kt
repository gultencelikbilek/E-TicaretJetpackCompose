package com.example.e_ticaret.presentation.shopping_cart_screen

import androidx.lifecycle.ViewModel
import com.example.e_ticaret.data.usecase.AllProductCartList
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val allProductCartList: AllProductCartList
) : ViewModel(){
}