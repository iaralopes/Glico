package com.example.iaralopes.glico.data.local.repository

import androidx.lifecycle.LiveData
import com.example.iaralopes.glico.data.local.dataBase.GlucoseDao
import com.example.iaralopes.glico.data.local.dataBase.GlucoseEntity
import com.example.iaralopes.glico.data.mapper.GlucoseMapper
import com.example.iaralopes.glico.data.remote.Glucose
import javax.inject.Inject

class LocalRepository
@Inject constructor(private val glucoseDao: GlucoseDao){

    fun getAllGlucoses(): LiveData<List<GlucoseEntity>> {
        return glucoseDao.getAllGlucoses()
    }

    fun getAllGlucosesToBackup(): List<GlucoseEntity> {
        return glucoseDao.getAllGlucosesToBackup()
    }

    fun saveGlucose(glucoseEntity: GlucoseEntity) {
        glucoseDao.insert(glucoseEntity)
    }

    fun saveGlucoses(glucoses: List<Glucose>) {
        for (glucose in glucoses) {
            glucoseDao.insert(GlucoseMapper.parseGlucoseRemoteToEntity(glucose))
        }
    }

    fun deleteGlucose(glucoseEntity: GlucoseEntity) {
        glucoseDao.delete(glucoseEntity)
    }


}