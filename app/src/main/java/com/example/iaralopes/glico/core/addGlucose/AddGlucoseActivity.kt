package com.example.iaralopes.glico.core.addGlucose

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants.Extras.Companion.OPTION_VISIBLE_EXTRA_BUNDLE
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_FILTER_EXTRA_BUNDLE
import com.example.iaralopes.glico.base.view.BaseActivity
import com.example.iaralopes.glico.core.selectCategory.SelectCategoryActivity
import com.example.iaralopes.glico.databinding.ActivityAddGlucoseBinding
import com.example.iaralopes.glico.extension.viewModel
import com.example.iaralopes.glico.utils.ErrorDialogFragment
import com.example.iaralopes.glico.utils.FlowState
import kotlinx.android.synthetic.main.partial_toolbar.*

class AddGlucoseActivity : BaseActivity() {

    private lateinit var binding: ActivityAddGlucoseBinding
    private lateinit var addGlucoseViewModel: AddGlucoseViewModel

    companion object {
        const val FILTER_REQUEST_CODE = 222
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addGlucoseViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_glucose)
        binding.viewModel = addGlucoseViewModel

        setUpToolbar(toolbar, "Hora do registro!")

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
                setResult(RESULT_OK, intent)
                finish()
            }
            FlowState.Status.ERROR -> {
                showErrorDialog(state.error?.message!!)
            }
        }
    }

    private fun showErrorDialog(errorMessage: String) {
        var dialog = ErrorDialogFragment()
        val arg = Bundle()
        arg.putString("textDialog", errorMessage)
        arg.putString("titleDialog", "Erro")
        dialog.arguments = arg
        dialog.show(supportFragmentManager, "SelectOptDialog")
    }

    fun onClickAddGlucose(view: View) {
        addGlucoseViewModel.addGlucose()
    }

    fun onClickSelectCategory(view: View) {
        val intent = Intent(this, SelectCategoryActivity::class.java)
        intent.putExtra(OPTION_VISIBLE_EXTRA_BUNDLE, false)
        startActivityForResult(
            intent,
            FILTER_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILTER_REQUEST_CODE && resultCode == RESULT_OK) {
            val result = data?.getStringExtra(RESULT_FILTER_EXTRA_BUNDLE).toString()

            addGlucoseViewModel.setCategory(result)

        }
    }


}
