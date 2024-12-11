package com.app.ecommerce.ui.activity.viewmodel

interface BaseNavigator {
    fun showProgressDialog()
    fun dismissProgressDialog()
    fun showMessage(code : Int, message: String?)
    fun onSessionExpired()
}