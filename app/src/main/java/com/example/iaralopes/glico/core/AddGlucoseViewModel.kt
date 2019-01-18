package com.example.iaralopes.glico.core

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.iaralopes.glico.data.GlucoseEntity
import com.example.iaralopes.glico.utils.FlowState
import javax.inject.Inject

class AddGlucoseViewModel @Inject constructor(val addGlucoseInteractor: AddGlucoseInteractor): ViewModel() {

    private val addGlucoseState = MediatorLiveData<FlowState<Unit>>()


    val resultCategory = ObservableField<String>()
    val resultData = ObservableField<String>()
    val resultValue = ObservableField<String>()

    fun addGlucose() {

        addGlucoseState.addSource(addGlucoseInteractor.addGlucose(GlucoseEntity(
            category = resultCategory.get().toString(),
            data = resultData.get().toString(),
            value = resultValue.get().toString()
        ))) {addGlucoseState.value = (it)}


    }

    fun addGlucoseState(): LiveData<FlowState<Unit>> = addGlucoseState
}