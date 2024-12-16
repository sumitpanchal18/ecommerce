package com.app.ecommerce.ui.productDetail.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
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
import com.app.ecommerce.ui.base.view.BaseFragment
import com.app.ecommerce.ui.cart.model.CartItem
import com.app.ecommerce.ui.cart.viewModel.CartViewModel
import com.app.ecommerce.ui.dashboard.model.DashBoardNavigator
import com.app.ecommerce.ui.productDetail.viewmodel.ProductDetailViewModel
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
            findNavController().navigate(R.id.action_productDetailFragment_to_dashboardFragment)
        }
        binding.imgShareBtnLogo.setOnClickListener {
            shareProductOnSocialMedia()
        }

        updateAddToCartButton()
    }

    private fun shareProductOnSocialMedia() {
        val product = productDetailViewModel.product.value ?: return
        val productTitle = product.title
        val productDescription = product.description
        val productUrl = "https://fakestoreapi.com/products/${product.id}"

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$productTitle\n\n$productDescription\n$productUrl")
            type = "text/plain"
        }

        val imageUri = Uri.parse(product.imageUrl)
        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri)
        shareIntent.type = "image/*"

        startActivity(Intent.createChooser(shareIntent, "Share via"))
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

    private fun updateAddToCartButton() {
        val isInCart = cartViewModel.isItemInCart(args.productId)
        if (isInCart) {
            binding.btnAddToCart.text = "Go To Cart"
            binding.btnAddToCart.setBackgroundResource(R.drawable.go_to_cart_bg)
            binding.btnAddToCart.setOnClickListener {
                val action =
                    ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment(
                        productId = args.productId,
                        productImage = args.productImage,
                        productTitle = args.productTitle,
                        productPrice = args.productPrice,
                        productCategory = args.productCategory,
                        productDes = args.productDes
                    )
                findNavController().navigate(action)
            }
        } else {
            binding.btnAddToCart.text = "Add To Cart"
            binding.btnAddToCart.setBackgroundResource(R.drawable.add_to_cart_bg)
            binding.btnAddToCart.setOnClickListener { handleAddToCart() }
        }
    }

    private fun formatPrice(price: Double): String {
        val decimalFormat = DecimalFormat("#.00")
        return decimalFormat.format(price)
    }

    override fun onBackClicked() {
        findNavController().navigate(R.id.action_productDetailFragment_to_dashboardFragment)
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

        if (cartViewModel.isItemInCart(cartItem.productId)) {
            val action = ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment(
                productId = cartItem.productId,
                productImage = cartItem.productImage,
                productTitle = cartItem.productTitle,
                productPrice = cartItem.productPrice.toFloat(),
                productCategory = product.category,
                productDes = product.description
            )
            findNavController().navigate(action)
        } else {
            cartViewModel.addToCart(cartItem)
            updateAddToCartButton()
        }
    }
}
