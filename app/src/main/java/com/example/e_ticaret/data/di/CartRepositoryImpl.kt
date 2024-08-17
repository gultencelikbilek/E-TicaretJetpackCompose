package com.example.e_ticaret.data.di

import android.content.Context
import com.example.e_ticaret.domain.model.ProductResponseItem
import com.example.e_ticaret.domain.model.ProductResponseItemDb
import com.example.e_ticaret.domain.repository.CartRespository
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(@ApplicationContext val  context: Context) : CartRespository {
    override suspend fun addProduct(productResponseItemDb: ProductResponseItemDb) {
        AppModule.prodivesRoomDatabase(context).cartDao.addCart(productResponseItemDb)
    }

    override suspend fun allProductDb(): List<ProductResponseItemDb> {
        return AppModule.providesCartImpl(context).allProductDb()
    }


}