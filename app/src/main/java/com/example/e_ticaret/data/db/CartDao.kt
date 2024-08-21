package com.example.e_ticaret.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.e_ticaret.domain.model.ProductResponseItemDb

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCart(productResponseItemDb: ProductResponseItemDb)

    @Query("SELECT * FROM productresponseitemdb")
    suspend fun allProduct(): List<ProductResponseItemDb>

    @Query("SELECT * FROM productresponseitemdb WHERE name = :name LIMIT 1")
    suspend fun getProductByName(name: String): ProductResponseItemDb?

    @Query("UPDATE productresponseitemdb SET quantity = :newQuantity WHERE name = :productName")
    suspend fun updateProductQuantity(productName: String, newQuantity: Int)

    @Delete
    suspend fun deleteProduct(productResponseItemDb: ProductResponseItemDb)
}
