package com.example.iaralopes.glico.core

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.*
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_FILTER_EXTRA_BUNDLE
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

    private var originalGlucoseList = listOf<GlucoseEntity>()

    companion object {
        const val FILTER_REQUEST_CODE = 222
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = homeViewModel

        setObservableViewModel()
        homeViewModel.getGlucoses()

        setUpHomeToolbar(toolbar, "Meu histórico")


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

                originalGlucoseList = it

                homeAdapter = HomeAdapter(it)
                binding.recyclerView.adapter = homeAdapter
                homeAdapter.notifyDataSetChanged()

            }
            FlowState.Status.ERROR -> {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.glico_menu, menu)
        val searchMenuItem = menu?.findItem(R.id.action_item_menu)
        searchMenuItem?.icon = getDrawable(R.drawable.ic_filter)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (it.itemId == R.id.action_item_menu) {
                val intent = Intent(this, FilterGlucoseActivity::class.java)
                startActivityForResult(intent, FILTER_REQUEST_CODE)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK) {
            var result = data?.getStringExtra(RESULT_FILTER_EXTRA_BUNDLE).toString()
            lateinit var sortGlucoseList: List<GlucoseEntity>
            when(result) {
                FilterGlucoseTypes.NENHUM.filter -> {
                    sortGlucoseList = originalGlucoseList
                }
                FilterGlucoseTypes.JEJUM.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.JEJUM.filter}
                }
                FilterGlucoseTypes.POSCAFE.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.POSCAFE.filter}
                }
                FilterGlucoseTypes.ANTESALMOCO.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.ANTESALMOCO.filter}
                }
                FilterGlucoseTypes.POSALMOCO.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.POSALMOCO.filter}
                }
                FilterGlucoseTypes.ANTESLANCHE.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.ANTESLANCHE.filter}
                }
                FilterGlucoseTypes.POSLANCHE.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.POSLANCHE.filter}
                }
                FilterGlucoseTypes.ANTESJANTAR.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.ANTESJANTAR.filter}
                }
                FilterGlucoseTypes.POSJANTAR.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.POSJANTAR.filter}
                }
                FilterGlucoseTypes.ANTESDORMIR.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.ANTESDORMIR.filter}
                }
                FilterGlucoseTypes.MADRUGADA.filter -> {
                    sortGlucoseList = originalGlucoseList.filter { it.category == FilterGlucoseTypes.MADRUGADA.filter}
                }
            }

            homeAdapter.list = sortGlucoseList
            homeAdapter.notifyDataSetChanged()

        }


    }
}
