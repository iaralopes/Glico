package com.example.iaralopes.glico.core.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.iaralopes.glico.app.Constants.SharedPreferences.Companion.USER_ID
import com.example.iaralopes.glico.data.local.preferences.PreferencesManager
import com.example.iaralopes.glico.data.local.repository.LocalRepository
import com.example.iaralopes.glico.data.remote.Login
import com.example.iaralopes.glico.data.remote.RemoteRepository
import com.example.iaralopes.glico.data.remote.Result
import com.example.iaralopes.glico.data.remote.User
import com.example.iaralopes.glico.utils.CoroutineContextProvider
import com.example.iaralopes.glico.utils.FlowState
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginInteractor @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val provider: CoroutineContextProvider,
    private val preferencesManager: PreferencesManager
) {

    private val resultLogin = MediatorLiveData<FlowState<User>>()

    fun login(loginBody: Login): LiveData<FlowState<User>> {
        GlobalScope.launch(provider.IO) {
            val resultRemote = remoteRepository.login(loginBody)
            handleLoginResult(resultRemote)
        }

        return this.resultLogin
    }

    private fun handleLoginResult(result: Result<User>) {
        when(result) {
            is Result.Success -> {
                resultLogin.postValue(FlowState(FlowState.Status.SUCCESS))
                saveUser(result.data)
            }
        }
    }

    private fun saveUser(user: User) {
        preferencesManager.setValue(USER_ID, user.id)
    }

}