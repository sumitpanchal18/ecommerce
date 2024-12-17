package com.app.ecommerce.ui.cart.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.FragmentCartBinding
import com.app.ecommerce.ui.base.view.BaseActivity.Companion.TAG
import com.app.ecommerce.ui.base.view.BaseFragment
import com.app.ecommerce.ui.cart.adapter.CartAdapter
import com.app.ecommerce.ui.cart.model.CartNavigator
import com.app.ecommerce.ui.cart.viewModel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : BaseFragment<FragmentCartBinding, CartViewModel>(), CartNavigator {

    private val cartViewModel: CartViewModel by activityViewModels()
    private lateinit var binding: FragmentCartBinding

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_cart

    override fun getViewModel(): CartViewModel = cartViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        binding.viewModel = cartViewModel
        setupRecyclerView()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        observeCartItems()

    }

    private fun setupRecyclerView() {
        binding.recyclerViewCart.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewCart.adapter = CartAdapter(cartViewModel) { cartItem ->
            val action = CartFragmentDirections.actionCartFragmentToProductDetailFragment(
                productId = cartItem.productId,
                productImage = cartItem.productImage,
                productTitle = cartItem.productTitle,
                productPrice = cartItem.productPrice.toFloat(),
                productCategory = "",
                productDes = ""
            )
            findNavController().navigate(action)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeCartItems() {
        cartViewModel.cartItems.observe(viewLifecycleOwner) { items ->
            // Log to confirm the data is being observed correctly
            Log.d(TAG, "Observed cart items: $items")
            // Update UI with the calculated data immediately after cart changes
            binding.txtTotalItems.text = "Items: ${cartViewModel.totalItems.value}"
            binding.txtSubtotal.text = "Subtotal: $${cartViewModel.subtotal.value}"
            binding.txtGST.text = "GST (5%): $${cartViewModel.gst.value}"
            binding.txtTotalPrice.text = "Total: $${cartViewModel.totalPrice.value}"

            // Manually notify changes to RecyclerView
            val adapter = binding.recyclerViewCart.adapter as CartAdapter
            adapter.submitList(items)
            adapter.notifyDataSetChanged()  // Notify the adapter of data changes
        }
    }


    override fun onItemClicked() {
    }
}
