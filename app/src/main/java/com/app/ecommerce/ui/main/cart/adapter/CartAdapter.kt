package com.app.ecommerce.ui.main.cart.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerce.databinding.ItemCartBinding
import com.app.ecommerce.ui.main.cart.model.CartItem
import com.app.ecommerce.ui.main.cart.viewModel.CartViewModel

class CartAdapter(private val cartViewModel: CartViewModel) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    private var items = mutableListOf<CartItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position], cartViewModel)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: MutableList<CartItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CartItem, viewModel: CartViewModel) {
            binding.item = item
            binding.viewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
