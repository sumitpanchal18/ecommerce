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

    private val _errorState = MutableLiveData<String?>()
    val errorState: LiveData<String?> get() = _errorState

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> get() = _loadingState

    fun fetchProducts() {
        viewModelScope.launch {
            _loadingState.value = true
            val result = repository.getProducts()

            when (result.status) {
                ResponseStatus.Status.SUCCESS -> {
                    result.body?.let {
                        Log.d(TAG, "Fetched Products: $it")
                        _productList.value = it
                        _errorState.value = null
                    } ?: run {
                        _errorState.value = "No products found"
                    }
                }
                ResponseStatus.Status.ERROR -> {
                    _errorState.value = result.message
                    Log.e(TAG, "Error fetching products: ${result.message}")
                }
                else -> {
                    _errorState.value = "Unexpected error"
                    Log.e(TAG, "Unexpected error fetching products")
                }
            }
            _loadingState.value = false
        }
    }
}
