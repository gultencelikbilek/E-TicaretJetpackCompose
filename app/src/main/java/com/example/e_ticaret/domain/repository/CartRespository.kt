package com.example.e_ticaret.domain.repository

import com.example.e_ticaret.domain.model.ProductResponseItemDb


interface CartRepository {
    suspend fun addOrUpdateProduct(productResponseItemDb: ProductResponseItemDb)

    suspend fun allProductDb(): List<ProductResponseItemDb>

    suspend fun updateProductQuantity(productName: String, newQuantity: Int)

    suspend fun getProductByName(productName: String): ProductResponseItemDb?
}