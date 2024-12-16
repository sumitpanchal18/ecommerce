package com.app.ecommerce.ui.dashboard.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.ecommerce.ui.dashboard.model.Product
import com.app.ecommerce.ui.base.view.BaseActivity.Companion.TAG
import com.app.ecommerce.ui.dashboard.model.DashBoardNavigator
import com.app.ecommerce.ui.dashboard.model.MainRepo
import com.app.ecommerce.ui.base.viewmodel.BaseViewModel
import com.app.ecommerce.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val repository: MainRepo
) : BaseViewModel<DashBoardNavigator>() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> get() = _productList

    fun fetchProducts() {
        viewModelScope.launch {
            val result = repository.getProducts()

            when (result.status) {
                ResponseStatus.Status.SUCCESS -> {
                    result.body?.let {
                        Log.d(TAG, "Fetched Products: $it")
                        _productList.value = it
                    }
                }
                else -> {
                    Log.e(TAG, "Error fetching products: ${result.message}")
                }
            }
        }
    }
}
