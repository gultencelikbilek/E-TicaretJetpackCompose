package com.example.e_ticaret.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductResponseItemDb(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name: String,
    val price: String,
    val url: String
)