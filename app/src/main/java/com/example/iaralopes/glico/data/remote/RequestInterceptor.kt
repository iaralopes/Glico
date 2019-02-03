package com.example.iaralopes.glico.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class RequestInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val builder = original.newBuilder()
//        val token = preferences.getString(USER_TOKEN)
        builder.header("Content-Type", "application/json")
//        if(!token.isNullOrBlank()) {
//            builder.header("Authorization", "Bearer $token")
//        }
        builder.method(original.method(), original.body())

        val newRequest = builder.build()

        return chain.proceed(newRequest)
    }

}
