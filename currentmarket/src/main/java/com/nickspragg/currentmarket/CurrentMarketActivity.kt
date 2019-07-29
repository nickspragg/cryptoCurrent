package com.nickspragg.currentmarket

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.nickspragg.core.di.CoreInjectHelper
import com.nickspragg.core.extensions.asDaysToEpoch
import com.nickspragg.core.extensions.formatCurrency
import com.nickspragg.currentmarket.di.CurrentMarketModule
import com.nickspragg.currentmarket.di.DaggerCurrentMarketComponent
import com.nickspragg.currentmarket.model.ChartData
import com.nickspragg.licence.LicenceActivity
import kotlinx.android.synthetic.main.activity_current_market.*
import kotlinx.android.synthetic.main.error_summary_view.*
import kotlinx.android.synthetic.main.summary_view.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class CurrentMarketActivity : AppCompatActivity(), CurrentMarketContract.View {

    @Inject
    lateinit var presenter: CurrentMarketContract.Presenter

    private val chartDateFormat = SimpleDateFormat(getString(R.string.chartDateFormat), Locale.ENGLISH)
    private val lastDateFormat = SimpleDateFormat(getString(R.string.lastUpdatedDateFormat), Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCurrentMarketComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .currentMarketModule(CurrentMarketModule(this))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_market)

        findViewById<Toolbar>(R.id.defaultToolbar)?.run {
            setSupportActionBar(this)
            getSupportActionBar()?.setDisplayShowTitleEnabled(false)
        }

        presenter.getMarketChart()
        presenter.getSummaryStats()

        swiperefresh.setOnRefreshListener {
            presenter.getMarketChart(true)
            presenter.getSummaryStats(true)
        }

        refreshButton.setOnClickListener {
            presenter.getMarketChart(true)
            presenter.getSummaryStats(true)
        }

        setupChart()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.licences -> {
                startActivity(Intent(this, LicenceActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setChartData(prices: List<ChartData.PricePoint>) {
        val values = ArrayList<Entry>().apply {
            prices.forEach {
                add(Entry(it.xValue.toFloat(), it.yValue.toFloat()))
            }
        }
        // create a dataset and give it a type
        val set1 = LineDataSet(values, "BTC Price (USD)").apply {
            setDrawCircles(false)
            lineWidth = 2f
            circleRadius = 3f
            fillAlpha = 255
            setDrawFilled(true)
            color = ContextCompat.getColor(this@CurrentMarketActivity, R.color.fillColour)
            fillColor = ContextCompat.getColor(this@CurrentMarketActivity, R.color.fillColour)
        }

        // create a data object with the data sets
        val data = LineData(set1).apply {
            setValueTextColor(Color.BLACK)
            setValueTextSize(9f)
        }

        // set data
        dailyChart.data = data
        dailyChart.notifyDataSetChanged()
        dailyChart.setVisibleXRange(7f.asDaysToEpoch(), 7f.asDaysToEpoch())
        prices.lastOrNull()?.run {
            dailyChart.moveViewToX(xValue.toFloat())
        }
    }

    override fun setCurrentPrice(price: Double) {
        currentPriceTxt.text = price.toFloat().formatCurrency()
    }

    override fun setTradeVolume(volume: Double) {
        tradeVolumeUsdTxt.text = volume.toFloat().formatCurrency()
    }

    override fun setLastUpdated(time: Long) {
        lastUpdatedDate.text = lastDateFormat.format(Date(time))
    }

    override fun showPlaceholderSummaryView(show: Boolean) {
        placeholderSummaryView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showSummaryView(show: Boolean) {
        summaryView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showErrorSummaryView(show: Boolean) {
        errorSummaryView.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun hideIsRefreshing() {
        swiperefresh.isRefreshing = false
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }

    private fun setupChart() {

        with(dailyChart) {
            setBackgroundColor(Color.WHITE)
            setBorderColor(Color.BLACK)
            setNoDataTextColor(Color.BLACK)
            setTouchEnabled(true)
            isDragEnabled = true
            axisRight.isEnabled = false
            axisLeft.isEnabled = false
        }

        with(dailyChart.xAxis) {
            position = XAxis.XAxisPosition.TOP
            textSize = 10f
            textColor = Color.WHITE
            setDrawAxisLine(false)
            setDrawGridLines(true)
            textColor = Color.BLACK
            setCenterAxisLabels(true)
            granularity = 1f.asDaysToEpoch() // one day
            valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val millis = value.toLong() * 1000
                    return chartDateFormat.format(Date(millis))
                }
            }
        }
    }
}