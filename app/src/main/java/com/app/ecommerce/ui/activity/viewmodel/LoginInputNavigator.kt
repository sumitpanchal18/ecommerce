package com.app.ecommerce.ui.activity.viewmodel


interface LoginInputNavigator : BaseNavigator {
    fun onLoginButtonClicked()
    fun onForgotPasswordClicked()
    fun onBackClicked()
    fun onSignUpClicked()
}