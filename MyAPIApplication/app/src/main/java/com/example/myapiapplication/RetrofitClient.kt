package com.example.myapiapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://fakestoreapi.com/"

interface ProductService {
    @GET("products")
    suspend fun getProducts(): List<Product>
}

object RetrofitClient {
    val service: ProductService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductService::class.java)
    }
}
