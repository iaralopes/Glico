package com.example.iaralopes.glico.app.di

import com.example.iaralopes.glico.app.GlicoApplication
import com.example.iaralopes.glico.base.view.BaseActivity
import com.example.iaralopes.glico.base.viewModel.ViewModelModule
import com.example.iaralopes.glico.data.local.di.GlicoDatabaseModule
import com.example.iaralopes.glico.data.local.di.LocalRepositoryModule
import com.example.iaralopes.glico.data.remote.RemoteRepository
import com.example.iaralopes.glico.data.remote.RemoteRepositoryModule
import com.example.iaralopes.glico.data.remote.ServiceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    GlicoDatabaseModule::class,
    LocalRepositoryModule::class,
    ViewModelModule::class,
    ServiceModule::class,
    RemoteRepositoryModule::class])interface ApplicationComponent {

    fun inject(application: GlicoApplication)
    fun inject(baseActivity: BaseActivity)
}