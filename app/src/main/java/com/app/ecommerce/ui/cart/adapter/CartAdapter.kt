package com.app.ecommerce.ui.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerce.databinding.ItemCartBinding
import com.app.ecommerce.ui.cart.model.CartItem
import com.app.ecommerce.ui.cart.viewModel.CartViewModel

class CartAdapter(
    private val cartViewModel: CartViewModel,
    private val itemClickListener: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var items = mutableListOf<CartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position], cartViewModel)
    }

    override fun getItemCount(): Int = items.size

    // Submit list of cart items to the adapter
    fun submitList(newItems: MutableList<CartItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CartItem, viewModel: CartViewModel) {
            binding.item = item
            binding.viewModel = viewModel

            // Set item details in the layout (image, title, price, etc.)
            binding.txtCartTitle.text = item.productTitle
            binding.txtCartPrice.text = "$${item.productPrice}"
            binding.txtCartQuantity.text = item.productQuantity.toString()

            // Handle increasing quantity
            binding.imgPlusItem.setOnClickListener {
                cartViewModel.increaseQuantity(item)
            }

            // Handle decreasing quantity
            binding.imgMinusItem.setOnClickListener {
                cartViewModel.decreaseQuantity(item)
            }

            // Handle removing item from the cart
            binding.imgRemove.setOnClickListener {
                cartViewModel.removeFromCart(item)
            }

            binding.executePendingBindings()

            // Handle item click if needed
            binding.root.setOnClickListener {
                itemClickListener(item)
            }
        }
    }
}
