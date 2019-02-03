package com.example.iaralopes.glico.data.local.dataBase

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(obj : T) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg obj : T)

    @Update
    fun update(obj : T)

    @Delete
    fun delete(obj : T)
}