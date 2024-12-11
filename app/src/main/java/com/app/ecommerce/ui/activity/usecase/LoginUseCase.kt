package com.app.ecommerce.ui.activity.usecase

import com.app.ecommerce.network.Product

interface LoginUseCase : BaseUseCase {

    suspend fun getProducts(): NetworkResponse<List<Product>, ApiError>
}
