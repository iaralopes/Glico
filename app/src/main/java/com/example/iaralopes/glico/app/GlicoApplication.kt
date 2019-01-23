package com.example.iaralopes.glico.app

import android.app.Application
import com.example.iaralopes.glico.app.di.ApplicationComponent
import com.example.iaralopes.glico.app.di.ApplicationModule
import com.example.iaralopes.glico.app.di.DaggerApplicationComponent

class GlicoApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}