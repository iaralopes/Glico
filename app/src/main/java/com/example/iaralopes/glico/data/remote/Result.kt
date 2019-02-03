package com.example.iaralopes.glico.data.remote

sealed class Result<out T> {

    class Success<T>(val data: T) : Result<T>()
    class Error(val exception: Throwable) : Result<Nothing>()
}