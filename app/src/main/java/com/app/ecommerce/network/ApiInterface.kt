package com.app.ecommerce.network

import com.app.ecommerce.ui.activity.usecase.ApiError
import com.app.ecommerce.ui.activity.usecase.NetworkResponse
import retrofit2.http.GET

interface ApiInterface {
    @GET("products")
    suspend fun getProducts(): NetworkResponse<List<Product>, ApiError>
}
