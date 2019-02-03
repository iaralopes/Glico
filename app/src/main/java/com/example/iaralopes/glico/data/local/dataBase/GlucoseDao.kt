package com.example.iaralopes.glico.data.local.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
abstract class GlucoseDao: BaseDao<GlucoseEntity> {

    @Insert(onConflict = OnConflictStrategy.FAIL)
    abstract override fun insert(obj: GlucoseEntity): Long

    @Query("SELECT * FROM glucose ORDER BY id DESC")
    abstract fun getAllGlucoses() : LiveData<List<GlucoseEntity>>

    @Query("SELECT * FROM glucose ORDER BY id DESC")
    abstract fun getAllGlucosesToBackup() : List<GlucoseEntity>

}