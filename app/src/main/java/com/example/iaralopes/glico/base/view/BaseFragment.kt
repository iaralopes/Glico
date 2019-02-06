package com.example.iaralopes.glico.base.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.iaralopes.glico.app.GlicoApplication
import com.example.iaralopes.glico.app.di.ApplicationComponent
import com.example.iaralopes.glico.base.viewModel.ViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        ( activity!!.application as GlicoApplication).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
    }
}