package com.example.iaralopes.glico.core

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.iaralopes.glico.R
import com.example.iaralopes.glico.app.Constants.Extras.Companion.OPTION_VISIBLE_EXTRA_BUNDLE
import com.example.iaralopes.glico.app.Constants.Extras.Companion.RESULT_FILTER_EXTRA_BUNDLE
import com.example.iaralopes.glico.base.view.BaseActivity
import com.example.iaralopes.glico.core.addGlucose.AddGlucoseActivity
import com.example.iaralopes.glico.core.home.HomeFragment
import com.example.iaralopes.glico.core.selectCategory.SelectCategoryActivity
import com.example.iaralopes.glico.core.utils.GlucoseTypes
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        const val FILTER_REQUEST_CODE = 222
        const val ADD_GLUCOSE_REQUEST_CODE = 333
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar(toolbar, "Meu histórico")

        fab.setOnClickListener {
            val intent = Intent(this, AddGlucoseActivity::class.java)
            startActivityForResult(
                intent,
                ADD_GLUCOSE_REQUEST_CODE
            )
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        openHomeFragment(GlucoseTypes.NENHUM.category)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.glico_menu, menu)
        val searchMenuItem = menu?.findItem(R.id.action_item_menu)
        searchMenuItem?.icon = getDrawable(R.drawable.ic_filter)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        item.let {
            if (it.itemId == R.id.action_item_menu) {
                val intent = Intent(this, SelectCategoryActivity::class.java)
                intent.putExtra(OPTION_VISIBLE_EXTRA_BUNDLE, true)
                startActivityForResult(
                    intent,
                    FILTER_REQUEST_CODE
                )
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_backup -> {

            }
            R.id.nav_logout -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == FILTER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra(RESULT_FILTER_EXTRA_BUNDLE).toString()
            openHomeFragment(result)
        }

        if (requestCode == ADD_GLUCOSE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            openHomeFragment(GlucoseTypes.NENHUM.category)
        }
    }

    private fun openHomeFragment(glucosesFilter: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.content_frame, HomeFragment.newInstance(glucosesFilter))
            .commit()
    }
}
