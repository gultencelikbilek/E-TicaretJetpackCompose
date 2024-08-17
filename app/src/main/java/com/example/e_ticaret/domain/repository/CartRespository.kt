package com.example.e_ticaret.domain.repository

import com.example.e_ticaret.domain.model.ProductResponseItem
import com.example.e_ticaret.domain.model.ProductResponseItemDb

interface CartRespository {
    suspend fun addProduct(productResponseItemDb: ProductResponseItemDb)

    suspend fun allProductDb() : List<ProductResponseItemDb>
}