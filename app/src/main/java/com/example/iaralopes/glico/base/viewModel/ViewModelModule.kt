package com.example.iaralopes.glico.base.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iaralopes.glico.base.viewModel.ViewModelFactory
import com.example.iaralopes.glico.base.viewModel.ViewModelKey
import com.example.iaralopes.glico.core.AddGlucoseViewModel
import com.example.iaralopes.glico.core.FilterGlucoseViewModel
import com.example.iaralopes.glico.core.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindsHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddGlucoseViewModel::class)
    abstract fun bindsAddGlucoseViewModel(addGlucoseViewModel: AddGlucoseViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FilterGlucoseViewModel::class)
    abstract fun bindsFilterGlucoseViewModel(filterGlucoseViewModel: FilterGlucoseViewModel): ViewModel


}