package com.example.iaralopes.glico.core

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.*
import com.example.iaralopes.glico.data.GlucoseEntity
import com.example.iaralopes.glico.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity
import com.example.iaralopes.glico.base.BaseActivity
import com.example.iaralopes.glico.utils.FlowState
import com.example.iaralopes.glico.extension.viewModel
import kotlinx.android.synthetic.main.partial_toolbar.toolbar

class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = homeViewModel

        setObservableViewModel()
        homeViewModel.getGlucoses()

        setUpHomeToolbar(toolbar, "Meu histórico" )


        fab.setOnClickListener { view ->
            startActivity<AddGlucoseActivity>()
        }

    }

    private fun setObservableViewModel() {
        homeViewModel.glucoseList().observe(this,
            Observer { it?.let { handleState(it) } })
    }

    private fun handleState(state: FlowState<List<GlucoseEntity>>) {
        when (state.status) {
            FlowState.Status.LOADING -> {
            }
            FlowState.Status.SUCCESS -> state.data?.let {
                homeAdapter = HomeAdapter(it)
                binding.recyclerView.adapter = homeAdapter
                homeAdapter.notifyDataSetChanged()

            }
            FlowState.Status.ERROR -> {

            }
        }
    }
}
