package com.example.pagoappmvvm.ui

import android.os.Bundle
import androidx.core.view.WindowCompat
import com.example.pagoappmvvm.R
import com.example.pagoappmvvm.databinding.ActivityMainBinding
import com.example.pagoappmvvm.ui.common.BaseActivity
import com.example.pagoappmvvm.ui.common.BaseViewModel

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    override fun getViewIdToFindNavController(): Int = R.id.nav_graph

    override fun getVM() = BaseViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStatusBarColor(R.color.white, true)
    }

    private fun setStatusBarColor(colorId: Int, isAppearanceLightStatusBars: Boolean) {
        this.window?.statusBarColor = getColor(colorId)
        WindowCompat.getInsetsController(this.window!!, this.window?.decorView!!)
            .isAppearanceLightStatusBars = isAppearanceLightStatusBars
    }
}