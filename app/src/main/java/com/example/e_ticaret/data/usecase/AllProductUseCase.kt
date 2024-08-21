package com.example.e_ticaret.data.usecase

import com.example.e_ticaret.data.network.NetworkResult
import com.example.e_ticaret.domain.model.ProductResponseItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.e_ticaret.data.di.ProductRepositoryImpl

class AllProductUseCase @Inject constructor(private val repositoryImpl: ProductRepositoryImpl) {

    operator suspend fun invoke() : Flow<NetworkResult<List<ProductResponseItem>>> = flow {
        emit(NetworkResult.Loading)
        try {
            val response = repositoryImpl.allProduct()
            emit(response)
        }catch (e:Exception){
            emit(NetworkResult.Error(e.message.toString()))
        }
    }
}