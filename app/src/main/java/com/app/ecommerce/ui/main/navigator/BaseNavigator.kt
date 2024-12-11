package com.app.ecommerce.ui.main.navigator

interface BaseNavigator {
    fun showProgressDialog()
    fun dismissProgressDialog()
    fun showMessage(code : Int, message: String?)
    fun onSessionExpired()
}