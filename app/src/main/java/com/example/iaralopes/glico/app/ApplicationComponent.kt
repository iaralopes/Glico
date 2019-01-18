package com.example.iaralopes.glico.app

import com.example.iaralopes.glico.base.BaseActivity
import com.example.iaralopes.glico.base.viewModel.ViewModelModule
import com.example.iaralopes.glico.data.GlicoDatabaseModule
import com.example.iaralopes.glico.data.LocalRepositoryModule
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