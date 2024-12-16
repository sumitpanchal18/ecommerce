package com.app.ecommerce.ui.main.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.ActivityDashBoardBinding
import com.app.ecommerce.ui.viewmodel.MainVM
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

        NavigationUI.setupWithNavController(binding.navigationView, navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.dashboardFragment) {
                binding.customToolbar.root.visibility = View.VISIBLE
            } else {
                binding.customToolbar.root.visibility = View.GONE
            }
        }
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    // Navigate to Home fragment
                    navController.navigate(R.id.dashboardFragment)
                    true
                }

                R.id.navigation_cart -> {
                    // Navigate to Wishlist fragment
                    navController.navigate(R.id.cartFragment)
                    true
                }

                R.id.navigation_wl -> {
                    // Navigate to Cart fragment
                    navController.navigate(R.id.cartFragment)
                    true
                }

                R.id.navigation_profile -> {
                    // Navigate to Profile fragment
                    navController.navigate(R.id.dashboardFragment)
                    true
                }

                R.id.navigation_settings -> {
                    // Navigate to Settings fragment
                    navController.navigate(R.id.dashboardFragment)
                    true
                }

                else -> false
            }
        }
        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_wishlist -> {
                    // Handle Wishlist action
                }

                R.id.nav_cart -> {
                    navController.navigate(R.id.action_dashboardFragment_to_cartFragment)
                }

                R.id.nav_profile -> {
                    // Handle Profile action
                }

                R.id.nav_settings -> {
                    // Handle Settings action
                }
            }
            binding.drawerLayout.closeDrawers()
            true
        }
        binding.customToolbar.menuIcon.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        binding.customToolbar.searchIcon.setOnClickListener {
            openSearch()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    private fun openSearch() {
        if (binding.clSearchBar.visibility == View.GONE) {
            binding.clSearchBar.visibility = View.VISIBLE
        } else {
            binding.clSearchBar.visibility = View.GONE
        }
    }
}
