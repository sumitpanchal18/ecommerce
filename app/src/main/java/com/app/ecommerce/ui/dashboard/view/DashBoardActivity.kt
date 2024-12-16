package com.app.ecommerce.ui.dashboard.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.ActivityDashBoardBinding
import com.app.ecommerce.ui.base.view.BaseActivity
import com.app.ecommerce.ui.dashboard.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, MainVM>() {

    private val mainVM: MainVM by viewModels()

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_dash_board
    override fun getViewModel(): MainVM = mainVM

    private lateinit var binding: ActivityDashBoardBinding
    private lateinit var navController: NavController

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()
        binding.customToolbar.toolbarTitle.text = "Dashboard"
        binding.customToolbar.toolbarSubtitle.text = "Welcome, User!"

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.dashboardFragment -> {
                    binding.customToolbar.root.visibility = View.VISIBLE
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.bottomNavigationView.menu.findItem(R.id.navigation_home).isChecked =
                        true
                }

                R.id.cartFragment -> {
                    binding.customToolbar.root.visibility = View.GONE
                    binding.clSearchBar.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.bottomNavigationView.menu.findItem(R.id.navigation_cart).isChecked =
                        true
                }

                R.id.wishlistFragment -> {
                    binding.clSearchBar.visibility = View.GONE
                    binding.customToolbar.root.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.bottomNavigationView.menu.findItem(R.id.navigation_wl).isChecked = true
                }

                R.id.accountFragment -> {
                    binding.customToolbar.root.visibility = View.GONE
                    binding.clSearchBar.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.VISIBLE
                    binding.bottomNavigationView.menu.findItem(R.id.navigation_account).isChecked =
                        true
                }

                R.id.productDetailFragment -> {
                    binding.clSearchBar.visibility = View.GONE
                    binding.customToolbar.root.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.GONE
                }

                else -> {
                    binding.customToolbar.root.visibility = View.GONE
                    binding.clSearchBar.visibility = View.GONE
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }

        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.dashboardFragment)
                    true
                }

                R.id.navigation_cart -> {
                    navController.navigate(R.id.cartFragment)
                    true
                }

                R.id.navigation_wl -> {
                    navController.navigate(R.id.wishlistFragment)
                    true
                }

                R.id.navigation_account -> {
                    navController.navigate(R.id.accountFragment)
                    true
                }

                else -> false
            }
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.dashboardFragment)
                }

                R.id.nav_wishlist -> {
                    navController.navigate(R.id.wishlistFragment)
                }

                R.id.nav_cart -> {
                    navController.navigate(R.id.cartFragment)
                }

                R.id.nav_account -> {
                    navController.navigate(R.id.accountFragment)
                }

                R.id.nav_logout -> {
                    // Handle logout action
                }
            }
            binding.drawerLayout.closeDrawers()
            true
        }

        binding.customToolbar.menuIcon.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        fetchProductsForSearch()
        binding.customToolbar.searchIcon.setOnClickListener {
            binding.clSearchBar.visibility =
                if (binding.clSearchBar.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun fetchProductsForSearch() {
        mainVM.productList.observe(this, Observer { products ->
            if (products.isNotEmpty()) {
                binding.etSearch.setText(products.first().title)
            }
        })
        mainVM.errorState.observe(this, Observer { error ->
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }
        })
        mainVM.loadingState.observe(this, Observer { isLoading ->
        })
    }
}
