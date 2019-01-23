package com.example.iaralopes.glico.data.di

import com.example.iaralopes.glico.data.repository.LocalRepository
import com.example.iaralopes.glico.data.dataBase.GlucoseDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalRepositoryModule {

    @Provides
    @Singleton
    fun provideLocalRepository(glucoseDao: GlucoseDao) : LocalRepository =
        LocalRepository(glucoseDao)

}