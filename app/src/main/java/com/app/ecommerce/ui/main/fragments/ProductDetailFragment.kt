package com.app.ecommerce.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.FragmentProductDetailBinding
import com.app.ecommerce.ui.main.activity.BaseActivity.Companion.TAG
import com.app.ecommerce.ui.main.navigator.DashBoardNavigator
import com.app.ecommerce.ui.viewmodel.ProductDetailViewModel
import com.bumptech.glide.Glide

class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>(),
    DashBoardNavigator {

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


    @SuppressLint("SetTextI18n")
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

    override fun onLoginButtonClicked() {
        TODO("Not yet implemented")
    }

    override fun onForgotPasswordClicked() {
        TODO("Not yet implemented")
    }

    override fun onBackClicked() {
        Log.d(TAG, "onBackClicked: ")
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
    }

    override fun onSignUpClicked() {
        TODO("Not yet implemented")
    }

    override fun showProgressDialog() {
        TODO("Not yet implemented")
    }

    override fun dismissProgressDialog() {
        TODO("Not yet implemented")
    }

    override fun showMessage(code: Int, message: String?) {
        TODO("Not yet implemented")
    }


}
