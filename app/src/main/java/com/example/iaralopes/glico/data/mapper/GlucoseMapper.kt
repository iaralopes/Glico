package com.example.iaralopes.glico.data.mapper

import com.example.iaralopes.glico.data.local.dataBase.GlucoseEntity
import com.example.iaralopes.glico.data.remote.Glucose

object GlucoseMapper {

    fun parseGlucoseRemoteToEntity(glucoseRemote: Glucose) : GlucoseEntity{
        return GlucoseEntity(
            category = glucoseRemote.category,
            data = glucoseRemote.date,
            value = glucoseRemote.value
        )
    }
}