package com.example.iaralopes.glico.core.selectCategory

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants.Extras.Companion.OPTION_VISIBLE_EXTRA_BUNDLE
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_FILTER_EXTRA_BUNDLE
import com.example.iaralopes.glico.base.view.BaseActivity
import com.example.iaralopes.glico.core.utils.GlucoseTypes
import com.example.iaralopes.glico.databinding.ActivitySelectCategoryBinding
import com.example.iaralopes.glico.extension.viewModel
import kotlinx.android.synthetic.main.partial_toolbar.*

class SelectCategoryActivity : BaseActivity() {

    private lateinit var binding: ActivitySelectCategoryBinding
    private lateinit var selectGlucoseViewModel: SelectCategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        selectGlucoseViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_select_category)
        binding.viewModel = selectGlucoseViewModel

        val optionIsVisible = intent.getBooleanExtra(OPTION_VISIBLE_EXTRA_BUNDLE, true)
        selectGlucoseViewModel.setOptionIsVisible(optionIsVisible)

        setUpToolbar(toolbar, "Selecionar categoria")
    }

    fun onClickNenhum (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.NENHUM.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickJejum (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.JEJUM.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosCafe (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.POSCAFE.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesAlmoco (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.ANTESALMOCO.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosAlmoco (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.POSALMOCO.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesLanche (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.ANTESLANCHE.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosLanche (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.POSLANCHE.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesJantar (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.ANTESJANTAR.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosJantar (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.POSJANTAR.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesDormir (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.ANTESDORMIR.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickMadrugada (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, GlucoseTypes.MADRUGADA.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickBackup(view: View) {
        selectGlucoseViewModel.backup()
    }
}
