package com.example.e_ticaret.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.e_ticaret.domain.model.ProductResponseItemDb

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(productResponseItemDb: ProductResponseItemDb)

    @Query("SELECT * FROM productresponseitemdb ORDER BY id ASC")
    suspend fun allProduct() : ProductResponseItemDb
}