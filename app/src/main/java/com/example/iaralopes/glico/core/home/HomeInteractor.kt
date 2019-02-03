package com.example.iaralopes.glico.core.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.iaralopes.glico.utils.CoroutineContextProvider
import com.example.iaralopes.glico.utils.FlowState
import com.example.iaralopes.glico.data.local.dataBase.GlucoseEntity
import com.example.iaralopes.glico.data.local.repository.LocalRepository
import com.example.iaralopes.glico.data.remote.Glucose
import com.example.iaralopes.glico.data.remote.RemoteRepository
import com.example.iaralopes.glico.data.remote.Result
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeInteractor
@Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val provider: CoroutineContextProvider
){

    private val resultGetGlucoses = MediatorLiveData<FlowState<List<GlucoseEntity>>>()

    fun getGlucoses(): LiveData<FlowState<List<GlucoseEntity>>> {
//        GlobalScope.launch(provider.IO) {
//            val resultRemote = remoteRepository.getGlucoses()
//            handleGlucoseRemoteResult(resultRemote)
//        }

        try {
            resultGetGlucoses.addSource(localRepository.getAllGlucoses()) {
                resultGetGlucoses.value = (FlowState(
                    FlowState.Status.SUCCESS,
                    it
                ))
            }
        }
        catch(ex: Exception){
            resultGetGlucoses.postValue(
                FlowState(
                    FlowState.Status.ERROR,
                    error = ex
                )
            )
        }

        return this.resultGetGlucoses

    }

    private fun handleGlucoseRemoteResult(resultRemote: Result<List<Glucose>>) {
        when (resultRemote) {
            is Result.Success -> {
                localRepository.saveGlucoses(resultRemote.data)
            }
        }
    }

    fun deleteGlucose(glucoseEntity: GlucoseEntity) {
        GlobalScope.launch(provider.IO) {
            localRepository.deleteGlucose(glucoseEntity)
        }
    }

}