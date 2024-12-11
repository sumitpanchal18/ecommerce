package com.app.ecommerce.ui.main.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.ActivityDashBoardBinding
import com.app.ecommerce.ui.main.fragments.DashBoardFragment
import com.app.ecommerce.ui.viewmodel.MainVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashBoardActivity : BaseActivity<ActivityDashBoardBinding, MainVM>() {

    private val mainVM: MainVM by viewModels()

    override fun getBindingVariable(): Int = BR.viewModel
    override fun getLayoutId(): Int = R.layout.activity_dash_board
    override fun getViewModel(): MainVM = mainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addFragment()
    }

    private fun addFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DashBoardFragment())
            .addToBackStack(null)
            .commit()
    }
}
