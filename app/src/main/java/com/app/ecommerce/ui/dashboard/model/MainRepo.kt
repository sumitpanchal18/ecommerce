package com.app.ecommerce.ui.dashboard.model

import com.app.ecommerce.ui.usecase.LoginUseCase
import com.app.ecommerce.ui.usecase.NetworkResponse
import com.app.ecommerce.utils.Constants
import com.app.ecommerce.utils.ResponseStatus
import javax.inject.Inject

class MainRepo @Inject constructor(private val useCase: LoginUseCase) {

    suspend fun getProducts(): ResponseStatus<List<Product>> {
        return when (val response = useCase.getProducts()) {
            is NetworkResponse.ApiError -> {
                ResponseStatus(
                    ResponseStatus.Status.ERROR,
                    response.code,
                    response.body.message
                )
            }
            is NetworkResponse.NetworkError -> {
                ResponseStatus(
                    ResponseStatus.Status.ERROR,
                    Constants.NETWORK_OTHER_ERROR_CODE, Constants.NETWORK_ERROR_MSG
                )
            }
            is NetworkResponse.Success -> {
                // Check if the response has the desired status
                if (response.body.isNotEmpty()) {
                    ResponseStatus(
                        ResponseStatus.Status.SUCCESS,
                        response.code,
                        body = response.body
                    )
                } else {
                    ResponseStatus(
                        ResponseStatus.Status.ERROR,
                        Constants.RESPONSE_CODE_201, Constants.TAG_MESSAGE
                    )
                }
            }
            is NetworkResponse.UnknownError -> {
                ResponseStatus(
                    ResponseStatus.Status.ERROR,
                    Constants.NETWORK_OTHER_ERROR_CODE, Constants.UNKNOWN_API_ERROR_MSG
                )
            }
        }
    }
}
