package com.example.iaralopes.glico.data.dataBase

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [GlucoseEntity::class],
    version = 1)
@TypeConverters()
abstract class GlicoDatabase : RoomDatabase() {

    abstract fun glucoseDao(): GlucoseDao


}
