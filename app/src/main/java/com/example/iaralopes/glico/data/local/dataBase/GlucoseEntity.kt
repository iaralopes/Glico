package com.example.iaralopes.glico.data.local.dataBase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "glucose")
data class GlucoseEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var category: String,
    var data: String,
    var value: String
)