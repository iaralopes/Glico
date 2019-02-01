package com.example.iaralopes.glico.core.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_FILTER_EXTRA_BUNDLE
import com.example.iaralopes.glico.base.view.BaseActivity
import com.example.iaralopes.glico.base.view.listeners.OnItemDialogFragmentClickListener
import com.example.iaralopes.glico.core.addGlucose.AddGlucoseActivity
import com.example.iaralopes.glico.core.home.adapter.HomeAdapter
import com.example.iaralopes.glico.core.home.utils.OnClickState
import com.example.iaralopes.glico.core.selectCategory.SelectCategoryActivity
import com.example.iaralopes.glico.core.utils.GlucoseTypes
import com.example.iaralopes.glico.data.dataBase.GlucoseEntity
import com.example.iaralopes.glico.databinding.ActivityHomeBinding
import com.example.iaralopes.glico.extension.viewModel
import com.example.iaralopes.glico.utils.AccessDialogFragment
import com.example.iaralopes.glico.utils.FlowState
import com.example.iaralopes.glico.utils.SelectOptDialogFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.partial_toolbar.toolbar
import org.jetbrains.anko.startActivity

class HomeActivity : BaseActivity(), OnItemDialogFragmentClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    private var onClickState = MutableLiveData<OnClickState<GlucoseEntity>>()

    private var originalGlucoseList = listOf<GlucoseEntity>()

    private lateinit var glucoseToDelete: GlucoseEntity

    companion object {
        const val FILTER_REQUEST_CODE = 222
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.viewModel = homeViewModel

        setObservableViewModel()
        setObservableItemAdapterState()

        showAccessPasswordDialog()

        setUpHomeToolbar(toolbar, "Meu histórico")


        fab.setOnClickListener { view ->
            startActivity<AddGlucoseActivity>()
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.glico_menu, menu)
        val searchMenuItem = menu?.findItem(R.id.action_item_menu)
        searchMenuItem?.icon = getDrawable(R.drawable.ic_filter)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            if (it.itemId == R.id.action_item_menu) {
                val intent = Intent(this, SelectCategoryActivity::class.java)
                intent.putExtra(Constants.Extras.OPTION_VISIBLE_EXTRA_BUNDLE, true)
                startActivityForResult(
                    intent,
                    FILTER_REQUEST_CODE
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

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

    private fun showAccessPasswordDialog() {
        var dialog = AccessDialogFragment()
        dialog.show(supportFragmentManager, "AccessPasswordDialog")
    }

    private fun showSelectOptionsDialog() {
        var dialog = SelectOptDialogFragment()
        val arg = Bundle()
        arg.putString("textDialog", "Deseja apagar a glicemia selecionada?")
        arg.putString("titleDialog", "Apagar glicemia")
        dialog.arguments = arg
        dialog.show(supportFragmentManager, "SelectOptDialog")
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
