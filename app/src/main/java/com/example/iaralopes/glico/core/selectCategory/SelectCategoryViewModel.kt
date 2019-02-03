package com.example.iaralopes.glico.core.selectCategory

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SelectCategoryViewModel @Inject constructor(
   val selectCategoryInteractor: SelectCategoryInteractor
): ViewModel() {

    val optionIsVisible = ObservableBoolean(true)

    fun setOptionIsVisible(isVisible: Boolean) {
        optionIsVisible.set(isVisible)
    }

    fun backup() {
        selectCategoryInteractor.backup()
    }
}