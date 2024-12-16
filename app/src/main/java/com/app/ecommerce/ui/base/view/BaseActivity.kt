package com.app.ecommerce.ui.base.view

import android.Manifest
import android.content.Context
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.app.ecommerce.ui.base.viewmodel.BaseViewModel

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity() {

    companion object {
        val TAG: String = BaseActivity::class.java.simpleName
        var isKeyboardVisible = false
        var handler: Handler? = null
    }

    private lateinit var viewDataBinding: T
    private var baseViewModel: V? = null
    private var navigateToSplash = false
    private var allowMultipleSelection: Boolean = false
    private var galleryPermissionsRequired = if (Build.VERSION.SDK_INT >= 33) {
        arrayOf(
            Manifest.permission.READ_MEDIA_IMAGES
        )
    } else {
        arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    val intentFilter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
        getDeviceSmallWidth()

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
            }
        })
    }

    private fun getDeviceSmallWidth() {
        val config = resources.configuration
        val screenWidthDp = config.smallestScreenWidthDp

        Log.e("Device sw value: %s", screenWidthDp.toString())
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.baseViewModel = getViewModel()
        viewDataBinding.setVariable(getBindingVariable(), baseViewModel)
        viewDataBinding.executePendingBindings()
    }

    fun getViewDataBinding(): T {
        return viewDataBinding
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    open fun dismissProgressDialog() {
    }
}
