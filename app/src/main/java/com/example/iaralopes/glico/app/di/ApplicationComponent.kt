package com.example.iaralopes.glico.app.di

import com.example.iaralopes.glico.app.GlicoApplication
import com.example.iaralopes.glico.base.view.BaseActivity
import com.example.iaralopes.glico.base.viewModel.ViewModelModule
import com.example.iaralopes.glico.data.di.GlicoDatabaseModule
import com.example.iaralopes.glico.data.di.LocalRepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    GlicoDatabaseModule::class,
    LocalRepositoryModule::class,
    ViewModelModule::class])interface ApplicationComponent {

    fun inject(application: GlicoApplication)
    fun inject(baseActivity: BaseActivity)
}