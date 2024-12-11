package com.app.ecommerce.ui.usecase

import com.app.ecommerce.network.ApiInterface


interface BaseUseCase {

    //suspend fun refreshToken(): ResponseStatus<Boolean>

}

open class BaseUseCaseImpl(
    private val apiService: ApiInterface
) : BaseUseCase {

    /* override suspend fun refreshToken(): ResponseStatus<Boolean> {
         val refreshTokenResponse = apiService.refreshToken(
             authDao?.loadRefreshToken()?.let {
                 RefreshTokenRequest(
                     Utils.encryptEmailForToken(authDao.loadEmail()),
                     it,
                     authDao.loadSessionToken(), JWT_TOKEN_REFRESH_API_VERSION
                 )
             }
         )
         when (refreshTokenResponse) {
             is NetworkResponse.Success -> {
                 if (refreshTokenResponse.body != null) {
                     val accessToken = refreshTokenResponse.body.token
                     val sessionToken = refreshTokenResponse.body.sessionToken
                     val refreshToken = refreshTokenResponse.body.refreshToken
                     authDao?.saveAccessToken(accessToken)
                     authDao?.saveSessionToken(sessionToken)
                     authDao?.saveRefreshToken(refreshToken)
                     return try {
                         Utils.saveSessionExpiryTime(refreshTokenResponse.body.sessionExpiresTime)
                         ResponseStatus(ResponseStatus.Status.SUCCESS, 200, "Success")
                     } catch (e: Exception) {
                         e.printStackTrace()
                         ResponseStatus(
                             ResponseStatus.Status.ERROR,
                             RESPONSE_CODE_UNAUTHORIZED,
                             "Session Expired"
                         )
                     }
                 } else {
                     return ResponseStatus(
                         ResponseStatus.Status.ERROR,
                         RESPONSE_CODE_UNAUTHORIZED,
                         "Session Expired"
                     )
                 }
             }
             is NetworkResponse.ApiError -> {
                 return if (refreshTokenResponse.code == 440) {
                     //ErrorCode = 440 ;  Session is expired; call refresh session API
                     refreshSessionToken()
                 } else {
                     ResponseStatus(
                         ResponseStatus.Status.ERROR,
                         RESPONSE_CODE_UNAUTHORIZED,
                         "Session Expired"
                     )
                 }
             }
             is NetworkResponse.NetworkError -> {
                 return ResponseStatus(
                     ResponseStatus.Status.ERROR,
                     Constants.NETWORK_OTHER_ERROR_CODE, Constants.NETWORK_ERROR_MSG
                 )
             }
             is NetworkResponse.UnknownError -> {
                 return ResponseStatus(
                     ResponseStatus.Status.ERROR,
                     Constants.NETWORK_OTHER_ERROR_CODE, Constants.UNKNOWN_API_ERROR_MSG
                 )
             }
         }
     }*/

}