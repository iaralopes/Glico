package com.example.iaralopes.glico.data.di

import android.content.Context
import androidx.room.Room
import com.example.iaralopes.glico.data.dataBase.GlicoDatabase
import com.example.iaralopes.glico.data.dataBase.GlucoseDao
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