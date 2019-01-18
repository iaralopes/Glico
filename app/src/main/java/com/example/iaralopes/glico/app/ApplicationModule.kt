package com.example.iaralopes.glico.app

import android.content.Context
import com.example.iaralopes.glico.utils.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context){

    @Provides
    @Singleton
    @Named("ApplicationContext")
    fun provideContext(): Context = context

    @Provides
    @Singleton
    fun provideCoroutineContextProvider(): CoroutineContextProvider
            = CoroutineContextProvider()

}