package com.example.e_ticaret.domain.repository

import com.example.e_ticaret.data.network.NetworkResult
import com.example.e_ticaret.domain.model.ProductResponseItem
import retrofit2.Response

interface ProductRepository {
    suspend fun allProduct():NetworkResult<List<ProductResponseItem>>

}