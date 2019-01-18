package com.example.iaralopes.glico.data

import android.content.Context
import androidx.room.Room
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