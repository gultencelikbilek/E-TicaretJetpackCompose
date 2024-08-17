package com.example.e_ticaret.data.usecase

import com.example.e_ticaret.data.di.CartRepositoryImpl
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import javax.inject.Inject

class AddProductUseCase @Inject constructor(private val cartRepositoryImpl: CartRepositoryImpl) {

    operator suspend fun invoke(productResponseItemDb: ProductResponseItemDb) = cartRepositoryImpl.addProduct(productResponseItemDb)
}