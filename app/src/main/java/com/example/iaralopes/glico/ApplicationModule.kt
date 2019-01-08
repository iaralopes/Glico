package com.example.iaralopes.glico

import android.content.Context
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

}