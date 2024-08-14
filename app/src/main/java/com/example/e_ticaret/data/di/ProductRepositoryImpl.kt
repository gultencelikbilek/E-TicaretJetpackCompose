package com.example.e_ticaret.data.di

import com.example.e_ticaret.data.network.NetworkResult
import com.example.e_ticaret.data.network.ProductService
import com.example.e_ticaret.domain.model.ProductResponseItem
import com.example.e_ticaret.domain.repository.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(private val productService: ProductService) : ProductRepository {
    override suspend fun allProduct(): NetworkResult<List<ProductResponseItem>> {
       return try {
           val response = productService.allProduct()
           if (response.isSuccessful && response.body() != null) {
                NetworkResult.Succes(response.body()!!)
            }else{
                NetworkResult.Error("Error: ${response.message()}")
            }
        }catch (e:Exception){
           NetworkResult.Error("Exception: ${e.message}")
        }
    }
}