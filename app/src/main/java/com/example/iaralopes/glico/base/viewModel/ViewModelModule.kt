package com.example.iaralopes.glico.base.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.iaralopes.glico.core.addGlucose.AddGlucoseViewModel
import com.example.iaralopes.glico.core.selectCategory.SelectCategoryViewModel
import com.example.iaralopes.glico.core.home.HomeViewModel
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
    @ViewModelKey(SelectCategoryViewModel::class)
    abstract fun bindsFilterGlucoseViewModel(filterGlucoseViewModel: SelectCategoryViewModel): ViewModel


}