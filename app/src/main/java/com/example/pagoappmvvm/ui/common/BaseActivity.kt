package com.example.pagoappmvvm.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavHost
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.example.pagoappmvvm.extensions.observe
import org.koin.core.component.KoinComponent

abstract class BaseActivity<B : ViewBinding>(private val viewBinder: (LayoutInflater) -> B) :
    AppCompatActivity(), KoinComponent {

    /**
     * This method is used to find the NavController
     * Should return the view id of a [NavHost] or a view within a [NavHost]
     */
    protected abstract fun getViewIdToFindNavController(): Int

    protected lateinit var binding: B

    abstract fun getVM(): BaseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinder.invoke(layoutInflater)
        setContentView(binding.root)

        with(getVM()) {
            observe(errorMessage) { message ->
                showToast(message)
            }
        }

    }

    protected fun navigate(@IdRes actionId: Int) {
        navigate(actionId, null)
    }

    protected fun navigate(@IdRes actionId: Int, args: Bundle?) {
        if (actionId == -1) {
            Toast.makeText(
                this,
                "Navigation destination not set yet!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            findNavController(getViewIdToFindNavController()).navigate(actionId, args)
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}