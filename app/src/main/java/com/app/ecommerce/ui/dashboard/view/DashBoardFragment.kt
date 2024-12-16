package com.app.ecommerce.ui.dashboard.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.FragmentDashboardBinding
import com.app.ecommerce.ui.base.view.BaseFragment
import com.app.ecommerce.ui.productDetail.model.Items
import com.app.ecommerce.ui.productDetail.adapter.ProductListAdapter
import com.app.ecommerce.ui.dashboard.viewmodel.MainVM
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
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        adapter = ProductListAdapter(emptyList(), onItemClick = {
            navigateToUserDetail(it)
        })
        setupRecyclerView()

        setupObservers()
        fetchProducts()
        return binding.root
    }


    private fun setupRecyclerView() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().finish()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun navigateToUserDetail(item: Items) {
        Log.d("DashBoardFragment", "Navigating to ProductDetailFragment with item: $item")
        val action = DashBoardFragmentDirections.actionDashboardFragmentToProductDetailFragment(
            productId = item.id,
            productImage = item.imageUrl,
            productTitle = item.title,
            productPrice = item.price.toFloat(),
            productCategory = item.category,
            productDes = item.description
        )
        findNavController().navigate(action)
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
