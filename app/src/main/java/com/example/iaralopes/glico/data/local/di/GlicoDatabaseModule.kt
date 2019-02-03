package com.example.iaralopes.glico.data.local.di

import android.content.Context
import androidx.room.Room
import com.example.iaralopes.glico.data.local.dataBase.GlicoDatabase
import com.example.iaralopes.glico.data.local.dataBase.GlucoseDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class GlicoDatabaseModule {

    @Provides
    @Singleton
    fun provideGlucoseDao(db : GlicoDatabase) : GlucoseDao =
        db.glucoseDao()

    @Provides
    @Singleton
    fun provideGlicoDataBase(@Named("ApplicationContext") context: Context) : GlicoDatabase =
        Room.databaseBuilder(context, GlicoDatabase::class.java, "database-glico")
            .fallbackToDestructiveMigration()
            .build()
}