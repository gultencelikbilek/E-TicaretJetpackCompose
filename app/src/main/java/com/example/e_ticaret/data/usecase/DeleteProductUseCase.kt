package com.example.e_ticaret.data.usecase


import com.example.e_ticaret.data.di.CartRepositoryImpl
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import javax.inject.Inject

class DeleteProductUseCase @Inject constructor(private val repository: CartRepositoryImpl) {
     operator fun invoke(productResponseItemDb: ProductResponseItemDb) {
        repository.deleteProduct(productResponseItemDb)
    }
}