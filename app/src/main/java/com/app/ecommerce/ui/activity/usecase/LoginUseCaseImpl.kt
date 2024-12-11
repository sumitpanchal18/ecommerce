package com.app.ecommerce.ui.activity.usecase

import com.app.ecommerce.network.ApiInterface
import com.app.ecommerce.network.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

data class ApiError(val code: Int = 0, val message: String)
class LoginUseCaseImpl @Inject constructor(
    private val api: ApiInterface
) : BaseUseCaseImpl(api), LoginUseCase {

    override suspend fun getProducts(): NetworkResponse<List<Product>, ApiError> =
        withContext(Dispatchers.IO) {
            api.getProducts()
        }
}
