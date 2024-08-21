package com.example.e_ticaret.data.di

import android.content.Context
import android.util.Log
import com.example.e_ticaret.data.db.CartDao
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import com.example.e_ticaret.domain.repository.CartRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CartRepository {

    private val cartDao: CartDao = AppModule.prodivesRoomDatabase(context).cartDao

    override suspend fun addOrUpdateProduct(productResponseItemDb: ProductResponseItemDb) {
        // 1. Ürünün veritabanında mevcut olup olmadığını kontrol et
        val existingProduct = cartDao.getProductByName(productResponseItemDb.name)

        // 2. Eğer ürün veritabanında mevcutsa
        if (existingProduct != null) {
            // Mevcut ürünün adet sayısını güncelle
            val newQuantity = existingProduct.quantity!! + productResponseItemDb.quantity!!
            cartDao.updateProductQuantity(productResponseItemDb.name, newQuantity)
        } else {
            // Ürün veritabanında mevcut değilse, yeni ürünü ekle
            cartDao.addCart(productResponseItemDb)
        }
    }

    override suspend fun allProductDb(): List<ProductResponseItemDb> {
        return cartDao.allProduct()
    }

    override suspend fun updateProductQuantity(productName: String, newQuantity: Int) {
        cartDao.updateProductQuantity(productName, newQuantity)
    }

    override suspend fun getProductByName(productName: String): ProductResponseItemDb? {
        return cartDao.getProductByName(productName)
    }

    override suspend fun deleteProduct(productResponseItemDb: ProductResponseItemDb) {
        cartDao.deleteProduct(productResponseItemDb)
    }
}