package com.app.ecommerce.ui.activity.ecommerce

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecommerce.R
import com.app.ecommerce.BR
import com.app.ecommerce.databinding.ActivityDashBoardBinding
import com.app.ecommerce.ui.activity.viewmodel.MainVM
import com.app.ecommerce.ui.activity.viewmodel.LoginInputNavigator
import com.app.ecommerce.ui.adapter.ProductListAdapter
import com.app.ecommerce.ui.adapter.Items
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, MainVM>(), LoginInputNavigator {

    private val mainVM: MainVM by viewModels()
    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var adapter: ProductListAdapter

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_dash_board
    override fun getViewModel(): MainVM = mainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        setupRecyclerView()
        setupObservers()
        fetchProducts()
    }

    private fun setupRecyclerView() {
        adapter = ProductListAdapter(emptyList())
        adapter.setOnItemClickListener { item ->
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product_id", item.id)
            intent.putExtra("product_image", item.imageUrl)
            intent.putExtra("product_title", item.title)
            intent.putExtra("product_price", item.price)
            intent.putExtra("product_category", item.category)
            intent.putExtra("product_des", item.description)
            startActivity(intent)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        mainVM.productList.observe(this) { products ->
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

    override fun onLoginButtonClicked() {
        Log.d("DashBoardActivity", "Login button clicked in Navigator")
    }

    override fun onForgotPasswordClicked() {
        Log.d("DashBoardActivity", "Forgot password clicked!")
    }

    override fun onBackClicked() {
        Log.d("DashBoardActivity", "Back button clicked!")
    }

    override fun onSignUpClicked() {
        Log.d("DashBoardActivity", "Sign up clicked!")
    }

    override fun showProgressDialog() {
        Log.d("DashBoardActivity", "Show progress dialog")
    }

    override fun dismissProgressDialog() {
        Log.d("DashBoardActivity", "Dismiss progress dialog")
    }

    override fun showMessage(code: Int, message: String?) {
        Log.d("DashBoardActivity", "Show message: $message with code $code")
    }

    override fun onSessionExpired() {
        Log.d("DashBoardActivity", "Session expired!")
    }
}
