package com.app.ecommerce.ui.cart.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.ecommerce.ui.cart.model.CartItem
import com.app.ecommerce.ui.cart.model.CartNavigator
import com.app.ecommerce.ui.base.viewmodel.BaseViewModel
import kotlin.math.roundToInt

class CartViewModel : BaseViewModel<CartNavigator>() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>(mutableListOf())
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    // Total price, subtotal, GST, and total price calculations
    private val _totalPrice = MutableLiveData<Double>(0.0)
    val totalPrice: LiveData<Double> = _totalPrice

    private val _subtotal = MutableLiveData<Double>(0.0)
    val subtotal: LiveData<Double> = _subtotal

    private val _gst = MutableLiveData<Double>(0.0)
    val gst: LiveData<Double> = _gst

    private val _totalItems = MutableLiveData<Int>(0)
    val totalItems: LiveData<Int> = _totalItems

    // Adding item to the cart
    fun addToCart(item: CartItem) {
        val currentList = _cartItems.value ?: mutableListOf()
        currentList.add(item)
        _cartItems.value = currentList
        updateCartCalculations()
    }

    // Checking if item already exists in the cart
    fun isItemInCart(productId: String): Boolean {
        return _cartItems.value.orEmpty().any {
            it.productId == productId
        }
    }

    // Increasing item quantity
    fun increaseQuantity(item: CartItem) {
        val currentList = _cartItems.value ?: return
        val index = currentList.indexOf(item)
        if (index >= 0) {
            val updatedQuantity = minOf(item.productQuantity + 1, 10) // limit to max 10 items
            currentList[index] = item.copy(productQuantity = updatedQuantity)
            _cartItems.value = currentList
            updateCartCalculations()
        }
    }

    // Decreasing item quantity
    fun decreaseQuantity(item: CartItem) {
        val currentList = _cartItems.value ?: return
        val index = currentList.indexOf(item)
        if (index != -1 && item.productQuantity > 1) {
            currentList[index] = item.copy(productQuantity = item.productQuantity - 1)
            _cartItems.value = currentList
            updateCartCalculations()
        }
    }

    // Removing item from the cart
    fun removeFromCart(item: CartItem) {
        val currentList = _cartItems.value ?: return
        currentList.remove(item)
        _cartItems.value = currentList
        updateCartCalculations()
    }

    // Update calculations whenever cart changes
    private fun updateCartCalculations() {
        val items = _cartItems.value.orEmpty()

        // Total items count
        _totalItems.value = items.sumOf { it.productQuantity }

        // Subtotal calculation
        val newSubtotal = items.sumOf { it.productPrice * it.productQuantity }
        _subtotal.value = newSubtotal

        // GST calculation (Assuming 5% GST)
        val newGST = newSubtotal * 0.05 // 5% GST
        _gst.value = newGST

        // Total price (Subtotal + GST)
        _totalPrice.value = newSubtotal + newGST
    }

    // For triggering item click event (Optional)
    fun onItemClicked() {
        navigator?.onItemClicked()
    }
}
