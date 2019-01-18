package com.example.iaralopes.glico.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.iaralopes.glico.utils.FlowState
import com.example.iaralopes.glico.data.GlucoseEntity
import javax.inject.Inject

class HomeViewModel
    @Inject constructor(val homeInteractor: HomeInteractor): ViewModel() {

    private val glucoseListState  = MediatorLiveData<FlowState<List<GlucoseEntity>>>()

    fun getGlucoses(){
        glucoseListState.postValue(FlowState(FlowState.Status.LOADING))
        glucoseListState.addSource(homeInteractor
            .getGlucoses()){glucoseListState.value = (it)}
    }

    fun glucoseList() : LiveData<FlowState<List<GlucoseEntity>>>
            = glucoseListState
}