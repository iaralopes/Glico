package com.example.iaralopes.glico.core.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants.FragmentArgs.Companion.GLUCOSE_FILTER_ARGS
import com.example.iaralopes.glico.base.view.BaseFragment
import com.example.iaralopes.glico.base.view.listeners.OnItemDialogFragmentClickListener
import com.example.iaralopes.glico.core.home.adapter.HomeAdapter
import com.example.iaralopes.glico.core.home.utils.OnClickState
import com.example.iaralopes.glico.core.utils.GlucoseTypes
import com.example.iaralopes.glico.data.local.dataBase.GlucoseEntity
import com.example.iaralopes.glico.databinding.FragmentHomeBinding
import com.example.iaralopes.glico.extension.viewModel
import com.example.iaralopes.glico.utils.FlowState
import com.example.iaralopes.glico.utils.SelectOptDialogFragment

class HomeFragment : BaseFragment(), OnItemDialogFragmentClickListener {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    private var onClickState = MutableLiveData<OnClickState<GlucoseEntity>>()

    private lateinit var glucoseToDelete: GlucoseEntity

    companion object {
        fun newInstance(glucosesFilter: String): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(GLUCOSE_FILTER_ARGS, glucosesFilter)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.viewModel = homeViewModel

        setObservableViewModel()
        setObservableItemAdapterState()

        homeViewModel.getGlucoses()


        return binding.root
    }

    private fun setObservableViewModel() {
        homeViewModel.glucoseList().observe(this,
            Observer { it?.let { handleGlucoseListState(it) } })
    }

    private fun setObservableItemAdapterState() {
        onClickState.observe(this,
            Observer { it?.let { handleItemAdapterState(it) } })
    }

    private fun handleGlucoseListState(state: FlowState<List<GlucoseEntity>>) {
        when (state.status) {
            FlowState.Status.LOADING -> {
            }
            FlowState.Status.SUCCESS -> state.data?.let {

                val filteredGlucoseList = filterGlucoses(it)
                homeViewModel.setGlucosesAverage(filteredGlucoseList)

                homeAdapter = HomeAdapter(filteredGlucoseList, onClickState)
                binding.recyclerView.adapter = homeAdapter
                homeAdapter.notifyDataSetChanged()

            }
            FlowState.Status.ERROR -> {

            }
        }
    }

    private fun filterGlucoses(originalGlucoseList: List<GlucoseEntity>): List<GlucoseEntity> {
        val filterType = arguments?.getString(GLUCOSE_FILTER_ARGS)
        lateinit var filteredGlucoseList: List<GlucoseEntity>

        when (filterType) {
            GlucoseTypes.NENHUM.category -> {
                filteredGlucoseList = originalGlucoseList
            }
            else -> {
                filteredGlucoseList = originalGlucoseList.filter { it.category == filterType }
            }
        }

        return filteredGlucoseList
    }

    private fun handleItemAdapterState(state: OnClickState<GlucoseEntity>) {
        when (state.status) {
            OnClickState.Status.DELETE -> state.data?.let {
                glucoseToDelete = it
                showSelectOptionsDialog()
            }
        }
    }

    private fun showSelectOptionsDialog() {
        var dialog = SelectOptDialogFragment()
        val arg = Bundle()
        arg.putString("textDialog", "Deseja apagar a glicemia selecionada?")
        arg.putString("titleDialog", "Apagar glicemia")
        dialog.arguments = arg
        dialog.setTargetFragment(this, 0)
        dialog.show(activity!!.supportFragmentManager, "SelectOptDialog")
    }

    override fun onItemDialogClick(id: Int) {
        when (id) {
            R.id.btn_positive -> {
                homeViewModel.deleteGlucose(glucoseToDelete)
            }
            R.id.btn_enter -> {
                homeViewModel.getGlucoses()
            }
        }
    }

}
