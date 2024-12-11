package com.app.ecommerce.ui.main.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.FragmentProductDetailBinding
import com.app.ecommerce.ui.viewmodel.ProductDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>() {

    private val productDetailViewModel: ProductDetailViewModel by viewModels()
    private lateinit var binding: FragmentProductDetailBinding

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_product_detail

    override fun getViewModel(): ProductDetailViewModel = productDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        setupProductDetails(binding)
        return binding.root
    }

    private fun setupProductDetails(binding: FragmentProductDetailBinding) {
        val productId = arguments?.getString("product_id") ?: ""
        val productImage = arguments?.getString("product_image") ?: ""
        val productTitle = arguments?.getString("product_title") ?: ""
        val productPrice = arguments?.getDouble("product_price", 0.0) ?: 0.0
        val productCategory = arguments?.getString("product_category") ?: ""
        val productDes = arguments?.getString("product_des") ?: ""

        productDetailViewModel.setProductDetails(
            productId, productImage, productTitle, productPrice, productCategory, productDes
        )

        productDetailViewModel.product.observe(viewLifecycleOwner) { product ->
            binding.txtTitle.text = product.title
            binding.txtPrice.text = "$ " + product.price.toString()
            binding.txtCategory.text = product.category
            binding.txtDescription.text = product.description

            Glide.with(requireContext()).load(product.imageUrl).placeholder(R.drawable.loading)
                .error(R.drawable.error).into(binding.imgProduct)
        }
    }
}
