package com.example.iaralopes.glico.data.local.di

import android.content.Context
import com.example.iaralopes.glico.data.local.preferences.PreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class PreferencesManagerModule {

    @Provides
    @Singleton
    fun providePreferencesManager(@Named("ApplicationContext") context: Context) : PreferencesManager =
        PreferencesManager(context)
}