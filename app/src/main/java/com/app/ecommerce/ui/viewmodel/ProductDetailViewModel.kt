package com.app.ecommerce.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.ecommerce.ui.adapter.Items
import com.app.ecommerce.ui.main.activity.BaseActivity.Companion.TAG
import com.app.ecommerce.ui.main.navigator.DashBoardNavigator
import com.app.ecommerce.utils.BaseViewModel


class ProductDetailViewModel : BaseViewModel<DashBoardNavigator>() {

    private val _product = MutableLiveData<Items>()
    val product: LiveData<Items> get() = _product

    fun onBackClicked() {
        Log.d(TAG, "ViewModel: Back clicked")
        navigator?.onBackClicked()
    }

    fun onAddToCartClicked() {
        navigator?.handleAddToCart()
    }

    fun setProductDetails(
        id: String,
        image: String,
        title: String,
        price: Double,
        category: String,
        description: String
    ) {
        val product = Items(
            id = id,
            imageUrl = image,
            title = title,
            price = price,
            category = category,
            description = description
        )
        _product.value = product
    }
}
