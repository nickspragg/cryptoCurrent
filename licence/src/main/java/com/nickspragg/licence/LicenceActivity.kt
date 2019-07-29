package com.nickspragg.licence

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nickspragg.core.di.CoreInjectHelper
import com.nickspragg.currentmarket.di.DaggerLicenceComponent
import com.nickspragg.licence.di.LicenceModule
import com.nickspragg.licence.viewholder.LicenceViewModel
import kotlinx.android.synthetic.main.activity_licences.*
import javax.inject.Inject

class LicenceActivity : AppCompatActivity(), LicenceContract.View {

    @Inject
    lateinit var presenter: LicenceContract.Presenter

    var adapter: LicenceAdapter = LicenceAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerLicenceComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .licenceModule(LicenceModule(this, this))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_licences)

        findViewById<Toolbar>(R.id.defaultToolbar)?.run {
            setSupportActionBar(this)
            getSupportActionBar()?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(true)
            }
            getNavigationIcon()?.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        }

        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        licenceRecyclerView.layoutManager = linearLayoutManager
        licenceRecyclerView.adapter = adapter

        presenter.init()
    }

    override fun showLicences(licences: List<LicenceViewModel>) {
        adapter.submitList(licences)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}