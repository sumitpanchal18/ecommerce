package com.app.ecommerce.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.ecommerce.R
import com.bumptech.glide.Glide

class ProductListAdapter(private var mList: List<Items>) :
    RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    private var onItemClickListener: ((Items) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<Items>) {
        mList = newList
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: (Items) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]

        Glide.with(holder.itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.loading)
            .error(R.drawable.error)
            .into(holder.imageView)
        holder.title.text = "Title : ${item.title}"
        holder.price.text = "Price : $${item.price}"
        holder.category.text = "Category : ${item.category}"
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(item)
        }
    }

    override fun getItemCount(): Int = mList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val price: TextView = itemView.findViewById(R.id.tvPrice)
        val category: TextView = itemView.findViewById(R.id.tvCategory)
    }
}
