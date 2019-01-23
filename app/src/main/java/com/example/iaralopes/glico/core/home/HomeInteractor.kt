package com.example.iaralopes.glico.core.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.iaralopes.glico.utils.CoroutineContextProvider
import com.example.iaralopes.glico.utils.FlowState
import com.example.iaralopes.glico.data.dataBase.GlucoseEntity
import com.example.iaralopes.glico.data.repository.LocalRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class HomeInteractor
@Inject constructor(
    private val localRepository: LocalRepository,
    private val provider: CoroutineContextProvider
){

    private val resultGetGlucoses = MediatorLiveData<FlowState<List<GlucoseEntity>>>()

    fun getGlucoses(): LiveData<FlowState<List<GlucoseEntity>>> {
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

    fun deleteGlucose(glucoseEntity: GlucoseEntity) {
        GlobalScope.launch(provider.IO) {
            localRepository.deleteGlucose(glucoseEntity)
        }
    }
}