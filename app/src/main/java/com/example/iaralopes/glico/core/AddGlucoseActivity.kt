package com.example.iaralopes.glico.core

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.base.BaseActivity
import com.example.iaralopes.glico.databinding.ActivityAddGlucoseBinding
import com.example.iaralopes.glico.extension.viewModel
import com.example.iaralopes.glico.utils.FlowState
import kotlinx.android.synthetic.main.partial_toolbar.toolbar
import org.jetbrains.anko.startActivity

class AddGlucoseActivity : BaseActivity() {

    private lateinit var binding: ActivityAddGlucoseBinding
    private lateinit var addGlucoseViewModel: AddGlucoseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addGlucoseViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_glucose)
        binding.viewModel = addGlucoseViewModel

        setUpToolbar(toolbar, "Hora do registro!" )

        setObservableViewModel()
    }

    private fun setObservableViewModel() {
        addGlucoseViewModel.addGlucoseState().observe(this,
            Observer { it?.let { handleState(it) } })
    }

    private fun handleState(state: FlowState<Unit>) {
        when (state.status) {
            FlowState.Status.LOADING -> {
            }
            FlowState.Status.SUCCESS -> {
                startActivity<HomeActivity>()
            }
            FlowState.Status.ERROR -> {
            }
        }
    }

    fun onClickAddGlucose(view: View) {
        addGlucoseViewModel.addGlucose()
    }
}
