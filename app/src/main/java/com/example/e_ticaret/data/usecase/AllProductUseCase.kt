package com.example.e_ticaret.data.usecase

import com.example.e_ticaret.data.di.ProductRepositoryImpl
import com.example.e_ticaret.data.network.NetworkResult
import com.example.e_ticaret.domain.model.ProductResponseItem
import javax.inject.Inject

class AllProductUseCase @Inject constructor(private val repositoryImpl: ProductRepositoryImpl) {

    operator suspend fun invoke() : NetworkResult<List<ProductResponseItem>> = repositoryImpl.allProduct()
}