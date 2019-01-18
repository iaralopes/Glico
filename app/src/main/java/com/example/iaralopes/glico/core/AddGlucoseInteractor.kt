package com.example.iaralopes.glico.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.iaralopes.glico.data.GlucoseEntity
import com.example.iaralopes.glico.data.LocalRepository
import com.example.iaralopes.glico.utils.CoroutineContextProvider
import com.example.iaralopes.glico.utils.FlowState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddGlucoseInteractor @Inject constructor(
    private val localRepository: LocalRepository,
    private val provider: CoroutineContextProvider
) {

    private val resultAddGlucose = MediatorLiveData<FlowState<Unit>>()

    fun addGlucose(glucoseEntity: GlucoseEntity): LiveData<FlowState<Unit>> {
        GlobalScope.launch(provider.IO) {
            try {
                localRepository.saveGlucose(glucoseEntity)
                resultAddGlucose.postValue(FlowState(FlowState.Status.SUCCESS))
            } catch (ex: Exception) {
                resultAddGlucose.postValue(FlowState(FlowState.Status.ERROR))
            }
        }

        return this.resultAddGlucose

    }
}