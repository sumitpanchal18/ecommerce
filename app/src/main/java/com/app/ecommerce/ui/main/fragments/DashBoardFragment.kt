package com.app.ecommerce.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.FragmentDashboardBinding
import com.app.ecommerce.ui.adapter.Items
import com.app.ecommerce.ui.adapter.ProductListAdapter
import com.app.ecommerce.ui.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardFragment : BaseFragment<FragmentDashboardBinding, MainVM>() {

    private val mainVM: MainVM by viewModels()
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var adapter: ProductListAdapter

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.fragment_dashboard
    override fun getViewModel(): MainVM = mainVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setupObservers()
        fetchProducts()
        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = ProductListAdapter(emptyList())
        adapter.setOnItemClickListener { item ->
            val fragment = ProductDetailFragment().apply {
                arguments = Bundle().apply {
                    putString("product_id", item.id)
                    putString("product_image", item.imageUrl)
                    putString("product_title", item.title)
                    putDouble("product_price", item.price)
                    putString("product_category", item.category)
                    putString("product_des", item.description)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)  // Add fragment to back stack for back navigation
                .commit()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        mainVM.productList.observe(viewLifecycleOwner) { products ->
            val dataList = products.map { product ->
                Items(
                    id = product.id.toString(),
                    imageUrl = product.image,
                    price = product.price,
                    title = product.title,
                    category = product.category,
                    description = product.description
                )
            }
            adapter.updateData(dataList)
        }
    }

    private fun fetchProducts() {
        mainVM.fetchProducts()
    }
}
