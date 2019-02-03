package com.example.iaralopes.glico.core.login

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.iaralopes.glico.core.home.HomeInteractor
import com.example.iaralopes.glico.data.remote.Login
import com.example.iaralopes.glico.data.remote.User
import com.example.iaralopes.glico.utils.FlowState
import javax.inject.Inject

class LoginViewModel @Inject constructor(val loginInteractor: LoginInteractor): ViewModel() {

    val resultUsername = ObservableField<String>()
    val resultPassword = ObservableField<String>()

    private val loginState  = MediatorLiveData<FlowState<User>>()

    fun login(){
        loginState.postValue(FlowState(FlowState.Status.LOADING))

        if(isValidBodyLogin()) {
            loginState.addSource(loginInteractor
                .login(
                    Login(
                        resultUsername.get()!!,
                        resultPassword.get()!!
                    )
                )){loginState.value = (it)}
        } else {
            loginState.postValue(FlowState(FlowState.Status.ERROR))
        }
    }

    private fun isValidBodyLogin(): Boolean {
        return !resultUsername.get().isNullOrEmpty() && !resultPassword.get().isNullOrEmpty()
    }

    fun loginState() : LiveData<FlowState<User>>
            = loginState
}