package com.example.iaralopes.glico.data.remote

import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GlicoService {

    @POST("users")
    fun login(@Body loginBody: Login): Deferred<User>

    @GET("glucoses")
    fun getGlucoses() : Deferred<List<Glucose>>

    @POST("glucoses")
    fun postGlucose(@Body glucose: Glucose): Deferred<ResponseBody>

}