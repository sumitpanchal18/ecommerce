package com.app.ecommerce.ui.main.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.app.ecommerce.BR
import com.app.ecommerce.R
import com.app.ecommerce.databinding.FragmentProductDetailBinding
import com.app.ecommerce.ui.main.cart.model.CartItem
import com.app.ecommerce.ui.main.cart.viewModel.CartViewModel
import com.app.ecommerce.ui.main.navigator.DashBoardNavigator
import com.app.ecommerce.ui.viewmodel.ProductDetailViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>(),
    DashBoardNavigator {

    private val productDetailViewModel: ProductDetailViewModel by viewModels()
    private val cartViewModel: CartViewModel by activityViewModels()
    private lateinit var binding: FragmentProductDetailBinding

    private val args: ProductDetailFragmentArgs by navArgs()

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_product_detail

    override fun getViewModel(): ProductDetailViewModel = productDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        binding.viewModel = productDetailViewModel
        productDetailViewModel.navigator = this
        setupProductDetails()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupProductDetails() {
        val productId = args.productId
        val productImage = args.productImage
        val productTitle = args.productTitle
        val productPrice = args.productPrice
        val productCategory = args.productCategory
        val productDes = args.productDes

        productDetailViewModel.setProductDetails(
            productId,
            productImage,
            productTitle,
            productPrice.toDouble(),
            productCategory,
            productDes
        )

        productDetailViewModel.product.observe(viewLifecycleOwner) { product ->
            binding.txtTitle.text = product.title
            binding.txtPrice.text = "$ " + formatPrice(product.price)
            binding.txtCategory.text = product.category
            binding.txtDescription.text = product.description

            Glide.with(requireContext()).load(product.imageUrl).placeholder(R.drawable.loading)
                .error(R.drawable.error).into(binding.imgProduct)
        }
    }

    private fun formatPrice(price: Double): String {
        val decimalFormat = DecimalFormat("#.00")
        return decimalFormat.format(price)
    }

    override fun onBackClicked() {
        findNavController().popBackStack()
    }

    override fun handleAddToCart() {
        val product = productDetailViewModel.product.value ?: return
        val cartItem = CartItem(
            productId = product.id,
            productImage = product.imageUrl,
            productTitle = product.title,
            productPrice = product.price,
            productQuantity = 1
        )
        cartViewModel.addToCart(cartItem)
        Log.d("ProductDetailFragment", "Navigating to CartFragment with item: $cartItem")
        val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment(
            productId = cartItem.productId,
            productImage = cartItem.productImage,
            productTitle = cartItem.productTitle,
            productPrice = cartItem.productPrice.toFloat(),
            productCategory = product.category,
            productDes = product.description
        )
        findNavController().navigate(action)
    }
}
