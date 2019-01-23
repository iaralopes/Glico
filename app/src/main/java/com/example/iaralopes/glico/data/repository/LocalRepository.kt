package com.example.iaralopes.glico.data.repository

import androidx.lifecycle.LiveData
import com.example.iaralopes.glico.data.dataBase.GlucoseDao
import com.example.iaralopes.glico.data.dataBase.GlucoseEntity
import javax.inject.Inject

class LocalRepository
@Inject constructor(private val glucoseDao: GlucoseDao){

    fun getAllGlucoses(): LiveData<List<GlucoseEntity>> {
        return glucoseDao.getAllGlucoses()
    }

    fun saveGlucose(glucoseEntity: GlucoseEntity) {
        glucoseDao.insert(glucoseEntity)
    }

    fun deleteGlucose(glucoseEntity: GlucoseEntity) {
        glucoseDao.delete(glucoseEntity)
    }


}