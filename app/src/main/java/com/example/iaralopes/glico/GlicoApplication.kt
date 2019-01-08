package com.example.iaralopes.glico

import android.app.Application
import com.example.iaralopes.glico.ApplicationModule
import com.example.iaralopes.glico.ApplicationComponent

class GlicoApplication : Application() {

    val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        this.injectMembers()
    }

    private fun injectMembers() = appComponent.inject(this)

}