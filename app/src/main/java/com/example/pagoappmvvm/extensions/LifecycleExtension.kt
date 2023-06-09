package com.example.pagoappmvvm.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData

fun <T> LifecycleOwner.observe(liveData: MutableLiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, { it?.let { t -> action(t) } })
}