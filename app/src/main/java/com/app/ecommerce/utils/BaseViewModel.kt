package com.app.ecommerce.utils

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel



abstract class BaseViewModel<N> : ViewModel() {

    var navigator: N? = null

    @JvmName("assignNavigator")
    fun setNavigator(navigator: N) {
        this.navigator = navigator
    }

    fun getBrowserIntent(url: String) = Intent(Intent.ACTION_VIEW, Uri.parse(url))


}