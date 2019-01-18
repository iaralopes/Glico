package com.example.iaralopes.glico.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_FILTER_EXTRA_BUNDLE
import com.example.iaralopes.glico.base.BaseActivity
import com.example.iaralopes.glico.databinding.ActivityFilterGlucoseBinding
import com.example.iaralopes.glico.extension.viewModel
import kotlinx.android.synthetic.main.partial_toolbar.*

class FilterGlucoseActivity : BaseActivity() {

    private lateinit var binding: ActivityFilterGlucoseBinding
    private lateinit var filterGlucoseViewModel: FilterGlucoseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        filterGlucoseViewModel = viewModel(viewModelFactory)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_filter_glucose)
        binding.viewModel = filterGlucoseViewModel

        setUpToolbar(toolbar, "Filtrar histórico")
    }

    fun onClickNenhum (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.NENHUM.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickJejum (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.JEJUM.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosCafe (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.POSCAFE.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesAlmoco (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.ANTESALMOCO.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosAlmoco (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.POSALMOCO.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesLanche (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.ANTESLANCHE.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosLanche (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.POSLANCHE.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesJantar (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.ANTESJANTAR.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosJantar (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.POSJANTAR.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesDormir (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.ANTESDORMIR.filter)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickMadrugada (view: View) {
        intent.putExtra(RESULT_FILTER_EXTRA_BUNDLE, FilterGlucoseTypes.MADRUGADA.filter)
        setResult(RESULT_OK, intent)
        finish()
    }
}
