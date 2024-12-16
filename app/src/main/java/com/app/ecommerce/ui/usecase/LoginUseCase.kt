package com.app.ecommerce.ui.usecase

import com.app.ecommerce.ui.dashboard.model.Product

interface LoginUseCase : BaseUseCase {

    suspend fun getProducts(): NetworkResponse<List<Product>, ApiError>
}
