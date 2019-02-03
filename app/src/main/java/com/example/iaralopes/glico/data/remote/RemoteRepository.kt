package com.example.iaralopes.glico.data.remote

import com.example.iaralopes.glico.extension.safeApiCall
import okhttp3.ResponseBody
import javax.inject.Inject

class RemoteRepository @Inject constructor(
    private val glicoService: GlicoService,
    private val networkStatus: NetworkStatus
) {

    suspend fun login(loginBody: Login): Result<User> {
        return safeApiCall(
            {networkStatus.isOnline()},
            {glicoService.login(loginBody).await() }
        )
    }

    suspend fun getGlucoses(): Result<List<Glucose>> {
        return safeApiCall(
            {networkStatus.isOnline()},
            { glicoService.getGlucoses().await() }
        )
    }

    suspend fun postGlucose(glucose: Glucose): Result<ResponseBody>{
        return safeApiCall(
            {networkStatus.isOnline()},
            { glicoService.postGlucose(glucose).await() }
        )
    }

}