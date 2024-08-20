package com.example.e_ticaret.data.usecase

import com.example.e_ticaret.data.di.CartRepositoryImpl
import javax.inject.Inject

class UpdateProductQuantityUseCase @Inject constructor(
    private val cartRepository: CartRepositoryImpl
) {

    suspend operator fun invoke(productName: String, newQuantity: Int) {
        cartRepository.updateProductQuantity(productName, newQuantity)
    }
}