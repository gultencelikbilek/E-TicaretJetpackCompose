package com.example.e_ticaret.data.usecase

import com.example.e_ticaret.data.di.CartRepositoryImpl
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import javax.inject.Inject

class GetProductByNameUseCase @Inject constructor(
    private val cartRepository: CartRepositoryImpl
) {

    suspend operator fun invoke(productName: String): ProductResponseItemDb? {
        return cartRepository.getProductByName(productName)
    }
}