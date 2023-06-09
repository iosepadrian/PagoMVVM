package com.example.pagoappmvvm.ui.common

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagoappmvvm.network.Resource
import com.example.pagoappmvvm.network.Status
import com.example.pagoappmvvm.utils.ConsumableLiveData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

open class BaseViewModel: ViewModel(), LifecycleObserver {

    val isLoading = ConsumableLiveData<Boolean>(true)

    var errorMessage = ConsumableLiveData<String>(true)

    fun setIsLoading(value: Boolean) {
        isLoading.setValue(value)
    }

    fun setErrorMessage(message: String?) {
        message?.let {
            if (it.isNotEmpty()) {
                errorMessage.setValue(it)
            }
        }
    }

    inline fun <T> launchAsync(
        crossinline execute: suspend () -> Flow<Resource<T>>,
        crossinline onSuccess: (T) -> Unit,
        crossinline onError: (Resource<T>) -> Unit = {},
        showProgress: Boolean = true
    ) {
        viewModelScope.launch {
            execute().collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        setIsLoading(false)
                        onSuccess.invoke(it.data!!)
                    }
                    Status.ERROR -> {
                        setErrorMessage(it.message)
                        setIsLoading(false)
                        onError.invoke(it)
                    }
                    Status.LOADING -> {
                        if (showProgress) {
                            setIsLoading(true)
                        }
                    }
                }
            }
        }
    }
}