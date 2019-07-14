package com.example.iaralopes.glico.core.selectCategory

import androidx.lifecycle.LiveData
import com.example.iaralopes.glico.data.local.dataBase.GlucoseEntity
import com.example.iaralopes.glico.data.local.preferences.PreferencesManager
import com.example.iaralopes.glico.data.local.repository.LocalRepository
import com.example.iaralopes.glico.data.remote.Glucose
import com.example.iaralopes.glico.data.remote.RemoteRepository
import com.example.iaralopes.glico.utils.CoroutineContextProvider
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class SelectCategoryInteractor @Inject constructor(
    private val localRepository: LocalRepository,
    private val remoteRepository: RemoteRepository,
    private val provider: CoroutineContextProvider,
    private val preferencesManager: PreferencesManager
) {

    fun backup() {
        GlobalScope.launch(provider.IO) {
            val userGlucoses = localRepository.getAllGlucosesToBackup()

            for (glucose in userGlucoses) {
                val resultRemote = remoteRepository.postGlucose(Glucose (
                    date = glucose.data,
                    category = glucose.category,
                    value = glucose.value,
                    userId = 1,
                    description = glucose.description
                ))
            }
        }
    }

}