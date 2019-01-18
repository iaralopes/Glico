package com.example.iaralopes.glico.data

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