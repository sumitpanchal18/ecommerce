package com.app.ecommerce.ui.cart.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.ecommerce.ui.base.viewmodel.BaseViewModel
import com.app.ecommerce.ui.cart.model.CartItem
import com.app.ecommerce.ui.cart.model.CartNavigator

class CartViewModel : BaseViewModel<CartNavigator>() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    private val _totalPrice = MutableLiveData(0.0)
    val totalPrice: LiveData<Double> = _totalPrice

    private val _subtotal = MutableLiveData(0.0)
    val subtotal: LiveData<Double> = _subtotal

    private val _gst = MutableLiveData(0.0)
    val gst: LiveData<Double> = _gst

    private val _totalItems = MutableLiveData(0)
    val totalItems: LiveData<Int> = _totalItems

    fun addToCart(item: CartItem) {
        val currentList = _cartItems.value ?: mutableListOf()
        currentList.add(item)
        _cartItems.value = currentList
//        Log.d("CartViewModel", "Item added to cart: ${item.productTitle}, Quantity: ${item.productQuantity}")
        updateCartCalculations()
    }

    fun isItemInCart(productId: String): Boolean {
        return _cartItems.value.orEmpty().any {
            it.productId == productId
        }
    }

    fun removeFromCart(item: CartItem) {
        val currentList = _cartItems.value ?: return
        currentList.remove(item)
        _cartItems.value = currentList
        Log.d("CartViewModel", "Item removed from cart: ${item.productTitle}")
        updateCartCalculations()
    }

    fun increaseQuantity(item: CartItem) {
        val currentList = _cartItems.value ?: return
        val index = currentList.indexOf(item)
        if (index >= 0) {
            val updatedQuantity = minOf(item.productQuantity + 1, 10)
            val updatedItem = item.copy(productQuantity = updatedQuantity)
            currentList[index] = updatedItem
            _cartItems.value = currentList
            Log.d(
                "CartViewModel",
                "Increased quantity for: ${updatedItem.productTitle}, New Quantity: ${updatedItem.productQuantity}"
            )
            updateCartCalculations()
        }
    }

    fun decreaseQuantity(item: CartItem) {
        val currentList = _cartItems.value ?: return
        val index = currentList.indexOf(item)
        if (index != -1 && item.productQuantity > 1) {
            val updatedItem = item.copy(productQuantity = item.productQuantity - 1)
            currentList[index] = updatedItem
            _cartItems.value = currentList
            Log.d(
                "CartViewModel",
                "Decreased quantity for: ${updatedItem.productTitle}, New Quantity: ${updatedItem.productQuantity}"
            )
            updateCartCalculations()
        }
    }

    private fun updateCartCalculations() {
        val items = _cartItems.value.orEmpty()

        _totalItems.value = items.sumOf { it.productQuantity }
        Log.d("CartViewModel", "Total items: ${_totalItems.value}")

        val newSubtotal = items.sumOf { it.productPrice * it.productQuantity }
        _subtotal.value = newSubtotal
        Log.d("CartViewModel", "Subtotal: $newSubtotal")

        val newGST = newSubtotal * 0.05
        _gst.value = newGST
        Log.d("CartViewModel", "GST (5%): $newGST")

        _totalPrice.value = newSubtotal + newGST
        Log.d("CartViewModel", "Total Price: ${_totalPrice.value}")
    }
}
