package com.example.e_ticaret.data.di

import android.content.Context
import androidx.room.Room
import com.example.e_ticaret.data.Constants
import com.example.e_ticaret.data.db.CartDatabase
import com.example.e_ticaret.data.network.ProductService
import com.example.e_ticaret.domain.repository.CartRespository
import com.example.e_ticaret.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun prodivesRoomDatabase(@ApplicationContext context: Context) : CartDatabase =
        Room.databaseBuilder(
            context,
            CartDatabase::class.java,
            "cart_db"
        ).build()

    @Provides
    @Singleton
    fun providesCartImpl(
        @ApplicationContext context: Context
    ) : CartRespository = CartRepositoryImpl(context)

    @Provides
    @Singleton
    fun providesRepoImpl(
        productService: ProductService
    ): ProductRepository = ProductRepositoryImpl(productService)

}