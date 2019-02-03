package com.example.iaralopes.glico.data.remote

import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface GlicoService {

    @GET("glucoses")
    fun getGlucoses() : Deferred<List<Glucose>>
}