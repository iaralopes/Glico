package com.example.iaralopes.glico.core.addGlucose

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.iaralopes.glico.data.dataBase.GlucoseEntity
import com.example.iaralopes.glico.utils.FlowState
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class AddGlucoseViewModel @Inject constructor(val addGlucoseInteractor: AddGlucoseInteractor) : ViewModel() {

    private val addGlucoseState = MediatorLiveData<FlowState<Unit>>()

    val resultCategory = ObservableField<String>()
    val resultValue = ObservableField<String>()

    init {
        resultCategory.set("clique para selecionar")
    }

    fun setCategory(result: String) {
        resultCategory.set(result)
    }

    fun addGlucose() {
        if (!resultValue.get().isNullOrEmpty()) {
            addGlucoseState.addSource(
                addGlucoseInteractor.addGlucose(
                    GlucoseEntity(
                        category = resultCategory.get().toString(),
                        data = getTodayDate(),
                        value = resultValue.get().toString()
                    )
                )
            ) { addGlucoseState.value = (it) }
        }
    }

    fun addGlucoseState(): LiveData<FlowState<Unit>> = addGlucoseState

    @SuppressLint("SimpleDateFormat")
    private fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        return dateFormat.format(Date())
    }
}