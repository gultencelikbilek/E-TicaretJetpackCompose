package com.example.e_ticaret.data.di

import com.example.e_ticaret.data.Constants
import com.example.e_ticaret.data.network.ProductService
import com.example.e_ticaret.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit()  : ProductService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepoImpl(
        productService: ProductService
    ):  ProductRepository = ProductRepositoryImpl(productService)

}