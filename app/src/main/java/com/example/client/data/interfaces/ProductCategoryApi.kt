package com.example.client.data.interfaces

import com.example.client.data.models.Category
import com.example.client.data.models.Product
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductCategoryApi {

    @GET("product")
    suspend fun getProducts(): List<Product>

    @POST("product")
    suspend fun postProduct(@Body product: Product): Product

    @GET("category")
    suspend fun getCategories(): List<Category>

}