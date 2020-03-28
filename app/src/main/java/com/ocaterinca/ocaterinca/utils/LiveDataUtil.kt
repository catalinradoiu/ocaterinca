package com.ocaterinca.ocaterinca.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

inline fun <T> dependantLiveData(vararg dependencies: LiveData<out Any>, defaultValue: T? = null, crossinline mapper: () -> T?): LiveData<T> =
    MediatorLiveData<T>().also { mediatorLiveData ->
        val observer = Observer<Any> { mediatorLiveData.value = mapper() }
        dependencies.forEach { dependencyLiveData ->
            mediatorLiveData.addSource(dependencyLiveData, observer)
        }
    }.apply { value = defaultValue }