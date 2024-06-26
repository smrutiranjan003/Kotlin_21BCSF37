package com.example.myapiapplication

class ProductRepository(private val apiService: ProductService) {
    suspend fun getProducts(): List<Product> {
        return apiService.getProducts()
    }
}
