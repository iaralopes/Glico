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
        if (hasGlucoseValue()) {
            val valueIsLessOrEqualThanThree = resultValue.get()!!.length <= 3
            val categoryIsNotEqualThanDefault = !resultCategory.get().equals("clique para selecionar")

            if (valueIsLessOrEqualThanThree && categoryIsNotEqualThanDefault) {
                addGlucoseState.addSource(
                    addGlucoseInteractor.addGlucose(
                        GlucoseEntity(
                            category = resultCategory.get().toString(),
                            data = getTodayDate(),
                            value = resultValue.get().toString()
                        )
                    )
                ) { addGlucoseState.value = (it) }
            } else {
                var errorMessage = "Confira se está tudo certo com os seus dados"

                if (!valueIsLessOrEqualThanThree) {
                    errorMessage = "Um valor de glicemia válido deve ter no máximo três algarismos"
                } else if (!categoryIsNotEqualThanDefault) {
                    errorMessage = "Você precisa selecionar uma categoria para registrar"
                }

                addGlucoseState.postValue(FlowState(FlowState.Status.ERROR, error = Throwable(errorMessage)))
            }

        }
    }

    private fun hasGlucoseValue(): Boolean {
        var hasGlucoseValue = false

        if (!resultValue.get().isNullOrEmpty()) {
            hasGlucoseValue = true
        } else {
            addGlucoseState.postValue(
                FlowState(
                    FlowState.Status.ERROR, error =
                    Throwable("Você precisa inserir o valor da glicemia para registrar")
                )
            )
        }

        return hasGlucoseValue
    }

    fun addGlucoseState(): LiveData<FlowState<Unit>> = addGlucoseState

    @SuppressLint("SimpleDateFormat")
    private fun getTodayDate(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")

        return dateFormat.format(Date())
    }

}