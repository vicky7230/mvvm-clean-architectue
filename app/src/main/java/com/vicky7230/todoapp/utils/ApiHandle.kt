package com.vicky7230.todoapp.utils

import com.vicky7230.todoapp.data.remote.NetworkResult
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber

object ApiHandle {
    suspend fun <T : Any> handleApi(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {
        return try {
            val response = execute()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                NetworkResult.Error(code = response.code(), message = response.message())
            }
        } catch (e: HttpException) {
            Timber.e("HttpException : ")
            Timber.e(e)
            NetworkResult.Error(code = e.code(), message = e.message())
        } catch (e: Throwable) {
            Timber.e("Throwable : ")
            Timber.e(e)
            NetworkResult.Exception(e)
        }
    }
}