package com.app.ecommerce.ui.base.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.app.ecommerce.R
import com.app.ecommerce.ui.dashboard.view.DashBoardActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        // Start Dashboard after 2 seconds
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, DashBoardActivity::class.java))
            finish()
        }, 5000)
    }
}
