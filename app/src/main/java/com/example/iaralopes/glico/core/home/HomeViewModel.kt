package com.example.iaralopes.glico.core.home

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.iaralopes.glico.utils.FlowState
import com.example.iaralopes.glico.data.local.dataBase.GlucoseEntity
import javax.inject.Inject

class HomeViewModel
    @Inject constructor(val homeInteractor: HomeInteractor): ViewModel() {

    val average = ObservableField("0")
    val averageVisibility = ObservableField(View.GONE)

    private val glucoseListState  = MediatorLiveData<FlowState<List<GlucoseEntity>>>()

    fun getGlucoses(){
        glucoseListState.postValue(FlowState(FlowState.Status.LOADING))
        glucoseListState.addSource(homeInteractor.getGlucoses()){glucoseListState.value = (it)}
    }

    fun glucoseList() : LiveData<FlowState<List<GlucoseEntity>>>
            = glucoseListState

    fun deleteGlucose(glucoseEntity: GlucoseEntity){
        homeInteractor.deleteGlucose(glucoseEntity)
    }

    fun setGlucosesAverage(glucoses: List<GlucoseEntity>) {
        if(glucoses.isNotEmpty()) {
            averageVisibility.set(View.VISIBLE)
            val values = glucoses.map { it.value.toInt() }
            val integerAverage = values.average().toInt()
            average.set(integerAverage.toString())
        }
    }
}