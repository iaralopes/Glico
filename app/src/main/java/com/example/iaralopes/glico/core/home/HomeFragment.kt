package com.example.iaralopes.glico.core.home

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_FILTER_EXTRA_BUNDLE
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

    private var originalGlucoseList = listOf<GlucoseEntity>()

    private lateinit var glucoseToDelete: GlucoseEntity

    companion object {
        const val FILTER_REQUEST_CODE = 222
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

                originalGlucoseList = it

                homeAdapter = HomeAdapter(it, onClickState)
                binding.recyclerView.adapter = homeAdapter
                homeAdapter.notifyDataSetChanged()

            }
            FlowState.Status.ERROR -> {

            }
        }
    }

    private fun handleItemAdapterState(state: OnClickState<GlucoseEntity>) {
        when (state.status) {
            OnClickState.Status.DELETE -> state.data?.let {
                glucoseToDelete = it
                showSelectOptionsDialog()
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.glico_menu, menu)
//        val searchMenuItem = menu?.findItem(R.id.action_item_menu)
//        searchMenuItem?.icon = getDrawable(R.drawable.ic_filter)
//
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        item?.let {
//            if (it.itemId == R.id.action_item_menu) {
//                val intent = Intent(this, SelectCategoryActivity::class.java)
//                intent.putExtra(Constants.Extras.OPTION_VISIBLE_EXTRA_BUNDLE, true)
//                startActivityForResult(
//                    intent,
//                    FILTER_REQUEST_CODE
//                )
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK) {
            var result = data?.getStringExtra(RESULT_FILTER_EXTRA_BUNDLE).toString()
            lateinit var sortGlucoseList: List<GlucoseEntity>
            when (result) {
                GlucoseTypes.NENHUM.category -> {
                    sortGlucoseList = originalGlucoseList
                }
                else -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == result }
                }
            }

            homeAdapter.list = sortGlucoseList
            homeAdapter.notifyDataSetChanged()

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
