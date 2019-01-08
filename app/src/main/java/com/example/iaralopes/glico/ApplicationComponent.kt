package com.example.iaralopes.glico

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class])interface ApplicationComponent {

    fun inject(application: GlicoApplication)
}