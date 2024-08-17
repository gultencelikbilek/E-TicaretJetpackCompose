package com.example.e_ticaret.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.e_ticaret.domain.model.ProductResponseItemDb

@Database(entities = [ProductResponseItemDb::class], version = 1)
abstract class CartDatabase : RoomDatabase(){
    abstract val cartDao :  CartDao
}