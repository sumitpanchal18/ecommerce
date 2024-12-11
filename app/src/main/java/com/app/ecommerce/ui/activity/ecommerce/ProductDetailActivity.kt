package com.app.ecommerce.ui.activity.ecommerce

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.viewModels
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.ActivityProductDetailBinding
import com.app.ecommerce.ui.activity.viewmodel.ProductDetailViewModel
import com.bumptech.glide.Glide

class ProductDetailActivity : BaseActivity<ActivityProductDetailBinding, ProductDetailViewModel>(),
    ProductDetailViewModel.OnBackClickListener {

    private val productDetailViewModel: ProductDetailViewModel by viewModels()

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_product_detail
    override fun getViewModel(): ProductDetailViewModel = productDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = getViewDataBinding()


        setupProductDetails(binding)
        productDetailViewModel.backClickListener = this
    }

    @SuppressLint("SetTextI18n")
    private fun setupProductDetails(binding: ActivityProductDetailBinding) {
        val productId = intent.getStringExtra("product_id") ?: ""
        val productImage = intent.getStringExtra("product_image") ?: ""
        val productTitle = intent.getStringExtra("product_title") ?: ""
        val productPrice = intent.getDoubleExtra("product_price", 0.0)
        val productCategory = intent.getStringExtra("product_category") ?: ""
        val productDes = intent.getStringExtra("product_des") ?: ""

        productDetailViewModel.setProductDetails(
            productId, productImage, productTitle, productPrice, productCategory, productDes
        )

        productDetailViewModel.product.observe(this) { product ->
            binding.txtTitle.text = product.title
            binding.txtPrice.text = "$ " +product.price.toString()
            binding.txtCategory.text = product.category
            binding.txtDescription.text = product.description

            Glide.with(this).load(product.imageUrl).placeholder(R.drawable.loading)
                .error(R.drawable.error).into(binding.imgProduct)
        }
    }

    override fun onBackClicked() {
        finish()
    }
}
