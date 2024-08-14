package com.example.e_ticaret.data.network

sealed class NetworkResult<out T> {
    data class Succes<out T>(val data : T) : NetworkResult<T>()
    data class Error(val errorMessage : String) : NetworkResult<Nothing>()
    object Loading: NetworkResult<Nothing>()
}