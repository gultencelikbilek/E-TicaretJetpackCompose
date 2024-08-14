package com.example.e_ticaret.data.network

import com.example.e_ticaret.data.Constants
import com.example.e_ticaret.domain.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {

    @GET(Constants.END_POINT)
    suspend fun allProduct() : Response<ProductResponse>
}