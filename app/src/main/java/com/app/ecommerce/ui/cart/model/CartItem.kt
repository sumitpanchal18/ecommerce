package com.app.ecommerce.ui.cart.model

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.ecommerce.R
import com.bumptech.glide.Glide
import java.text.DecimalFormat

data class CartItem(
    val productId: String,
    val productImage: String,
    val productTitle: String,
    val productPrice: Double,
    val productQuantity: Int
)

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, url: String?) {
    Glide.with(view.context)
        .load(url)
        .placeholder(R.drawable.loading)
        .error(R.drawable.error)
        .into(view)
}


@BindingAdapter("formattedPrice")
fun bindFormattedPrice(view: TextView, price: Double) {
    val decimalFormat = DecimalFormat("#.00")
    view.text = "$ " + decimalFormat.format(price)
}

