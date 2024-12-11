package com.app.ecommerce.ui.activity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.ecommerce.ui.adapter.Items
import com.app.ecommerce.utils.BaseViewModel


class ProductDetailViewModel : BaseViewModel<Items>() {

    private val _product = MutableLiveData<Items>()
    val product: LiveData<Items> get() = _product

    interface OnBackClickListener {
        fun onBackClicked()
    }

    var backClickListener: OnBackClickListener? = null

    fun onBackClick() {
        backClickListener?.onBackClicked()
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
