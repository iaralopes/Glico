package com.example.iaralopes.glico.data.remote

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteRepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(glicoService: GlicoService, networkStatus: NetworkStatus) : RemoteRepository =
        RemoteRepository(glicoService, networkStatus)
}