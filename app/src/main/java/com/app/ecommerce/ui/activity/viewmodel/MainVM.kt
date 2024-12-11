package com.app.ecommerce.ui.activity.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.ecommerce.network.Product
import com.app.ecommerce.ui.activity.ecommerce.BaseActivity.Companion.TAG
import com.app.ecommerce.ui.activity.repo.MainRepo
import com.app.ecommerce.utils.BaseViewModel
import com.app.ecommerce.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val repository: MainRepo
) : BaseViewModel<LoginInputNavigator>() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList

    fun fetchProducts() {
        navigator?.showProgressDialog()
        viewModelScope.launch {
            val result = repository.getProducts()
            navigator?.dismissProgressDialog()

            when (result.status) {
                ResponseStatus.Status.SUCCESS -> {
                    result.body?.let {
                        Log.d(TAG, "Fetched Products: $it")
                        _productList.value = it
                    }
                }
                else -> {
                    Log.e(TAG, "Error fetching products: ${result.message}")
                    navigator?.showMessage(result.code, result.message)
                }
            }
        }
    }
}
