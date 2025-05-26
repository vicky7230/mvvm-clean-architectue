package com.vicky7230.todoapp.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Util(val dispatcher: CoroutineDispatcher) {

    suspend fun getUserName(): String {
        delay(10000)
        return "Vipin"
    }

    suspend fun getUser(): String {
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
        }
        return "User - Vipin"
    }

    suspend fun getAddress(): String {
        withContext(dispatcher) {
            delay(5000)
        }
        return "Address"
    }

    var globalArg = false
    fun getAddressDetail() {
        CoroutineScope(dispatcher).launch {
            globalArg = true
        }
    }
}