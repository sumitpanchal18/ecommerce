package com.app.ecommerce.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.app.ecommerce.ui.main.activity.BaseActivity
import com.app.ecommerce.ui.main.activity.BaseActivity.Companion.TAG
import com.app.ecommerce.utils.BaseViewModel

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

    private var baseActivity: BaseActivity<*, *>? = null

    private lateinit var rootView: View

    private lateinit var viewDataBinding: T

    private lateinit var baseViewModel: V

    abstract fun getBindingVariable(): Int

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    open fun onSessionExpired() {}

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity<*, *>) {
            val activity: BaseActivity<*, *> = context
            this.baseActivity = activity
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        rootView = viewDataBinding.root
        return rootView
    }

    // Use this method to access the binding safely
    fun fetchViewDataBinding(): T {
        if (!::viewDataBinding.isInitialized) {
            throw UninitializedPropertyAccessException("viewDataBinding is not initialized")
        }
        return viewDataBinding
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    fun getBaseActivity(): BaseActivity<*, *>? {
        return baseActivity
    }


}
