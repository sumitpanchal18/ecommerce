package com.app.ecommerce.ui.cart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.ecommerce.ui.cart.model.CartItem
import com.app.ecommerce.ui.cart.model.CartNavigator
import com.app.ecommerce.ui.base.viewmodel.BaseViewModel

class CartViewModel : BaseViewModel<CartNavigator>() {
    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    fun onItemClicked() {
        navigator?.onItemClicked()
    }

    fun addToCart(item: CartItem) {
        val currentList = _cartItems.value ?: mutableListOf()
        currentList.add(item)
        _cartItems.value = currentList
    }

    fun isItemInCart(productId: String): Boolean {
        return _cartItems.value.orEmpty().any {
            it.productId == productId
        }
    }

    fun increaseQuantity(item: CartItem) {
        val currentList = _cartItems.value ?: return
        val index = currentList.indexOf(item)
        if (index >= 0) {
            val updatedQuantity = minOf(item.productQuantity + 1, 10)
            currentList[index] = item.copy(productQuantity = updatedQuantity)
            _cartItems.value = currentList
        }
    }


    fun decreaseQuantity(item: CartItem) {
        val currentList = _cartItems.value ?: return
        val index = currentList.indexOf(item)
        if (index != -1 && item.productQuantity > 1) {
            currentList[index] = item.copy(productQuantity = item.productQuantity - 1)
            _cartItems.value = currentList
        }
    }

    fun removeFromCart(item: CartItem) {
        val currentList = _cartItems.value ?: return
        currentList.remove(item)
        _cartItems.value = currentList
    }
}

