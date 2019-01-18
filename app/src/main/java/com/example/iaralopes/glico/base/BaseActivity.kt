package com.example.iaralopes.glico.base

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.ApplicationComponent
import com.example.iaralopes.glico.app.GlicoApplication
import com.example.iaralopes.glico.base.viewModel.ViewModelFactory
import java.lang.Exception
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var mToolbar: Toolbar

    protected val appComponent: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.NONE) {
        (application as GlicoApplication).appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appComponent.inject(this)
    }

    protected fun setUpHomeToolbar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar.let {
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            mToolbar = toolbar
            setTitleToolbar(title)
        }
    }

    protected fun setUpToolbar(toolbar: Toolbar, title: String) {
        setSupportActionBar(toolbar)
        supportActionBar.let {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
            toolbar.setNavigationIcon(R.drawable.ic_back)
            toolbar.setNavigationOnClickListener { onBackPressed() }
            mToolbar = toolbar
            setTitleToolbar(title)
        }
    }

    fun setTitleToolbar(title: String) {
        try {
            (mToolbar.findViewById(R.id.toolbar_title) as TextView).text = title
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}