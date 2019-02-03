package com.example.iaralopes.glico.data.local.di

import com.example.iaralopes.glico.data.local.repository.LocalRepository
import com.example.iaralopes.glico.data.local.dataBase.GlucoseDao
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