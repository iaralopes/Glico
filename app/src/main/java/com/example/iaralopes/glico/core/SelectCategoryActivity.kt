package com.example.iaralopes.glico.core

import android.os.Bundle
import android.view.View
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_SELECT_EXTRA_BUNDLE
import com.example.iaralopes.glico.base.BaseActivity
import kotlinx.android.synthetic.main.partial_toolbar.*

class SelectCategoryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_category)

        setUpToolbar(toolbar, "Selecionar categoria")
    }

    fun onClickJejum (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.JEJUM.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosCafe (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.POSCAFE.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesAlmoco (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.ANTESALMOCO.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosAlmoco (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.POSALMOCO.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesLanche (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.ANTESLANCHE.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosLanche (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.POSLANCHE.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesJantar (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.ANTESJANTAR.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickPosJantar (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.POSJANTAR.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickAntesDormir (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.ANTESDORMIR.category)
        setResult(RESULT_OK, intent)
        finish()
    }

    fun onClickMadrugada (view: View) {
        intent.putExtra(RESULT_SELECT_EXTRA_BUNDLE, GlucoseTypes.MADRUGADA.category)
        setResult(RESULT_OK, intent)
        finish()
    }
}
