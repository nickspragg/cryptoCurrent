package com.nickspragg.currentmarket

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.nickspragg.core.di.CoreInjectHelper
import com.nickspragg.core.extensions.formatCurrency
import com.nickspragg.currentmarket.di.CurrentMarketModule
import com.nickspragg.currentmarket.di.DaggerCurrentMarketComponent
import com.nickspragg.currentmarket.model.ChartData
import kotlinx.android.synthetic.main.activity_current_market.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CurrentMarketActivity : AppCompatActivity(), CurrentMarketContract.View {

    @Inject
    lateinit var presenter: CurrentMarketContract.Presenter

    private val chartDateFormat = SimpleDateFormat("dd MMM", Locale.ENGLISH)
    private val lastDateFormat = SimpleDateFormat("YYYY/mm/dd HH:mm:ss", Locale.ENGLISH)

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

        with(dailyChart) {
            setBackgroundColor(Color.WHITE)
            setBorderColor(Color.BLACK)
            setNoDataTextColor(Color.BLACK)
            description.setEnabled(true)
            setTouchEnabled(true)
            // create marker to display box when values are selected
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            setVisibleXRange(7f,7f)
            axisRight.isEnabled = false
            axisLeft.isEnabled = false
        }

        with(dailyChart.xAxis) {
            setPosition(XAxis.XAxisPosition.TOP)
            setTextSize(10f)
            setTextColor(Color.WHITE)
            setDrawAxisLine(false)
            setDrawGridLines(true)
            setTextColor(Color.BLACK)
            setCenterAxisLabels(true)
            setGranularity(1f) // one hour
            setValueFormatter(object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    val millis = value.toLong() * 1000
                    return chartDateFormat.format(Date(millis))
                }
            })
        }
    }

    override fun showMarketChart(prices: List<ChartData.PricePoint>) {
        val values = ArrayList<Entry>().apply {
            prices.forEach {
                add(Entry(it.xValue.toFloat(), it.yValue.toFloat()))
            }
        }
        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1").apply {
            setDrawCircles(false)
            setLineWidth(2f)
            setCircleRadius(3f)
            setFillAlpha(255)
            setDrawFilled(true)
            color = ContextCompat.getColor(this@CurrentMarketActivity, R.color.fillColour)
            setFillColor(ContextCompat.getColor(this@CurrentMarketActivity, R.color.fillColour))
        }

        // create a data object with the data sets
        val data = LineData(set1).apply {
            setValueTextColor(Color.BLACK)
            setValueTextSize(9f)
        }

        // set data
        dailyChart.setData(data)
        dailyChart.invalidate()
    }

    override fun showCurrentPrice(price: Double) {
        currentPriceTxt.text = price.toFloat().formatCurrency()
    }

    override fun showTradeVolume(volume: Double) {
        tradeVolumeUsdTxt.text = volume.toFloat().formatCurrency()
    }

    override fun showLastUpdated(time: Long) {
        val millis = TimeUnit.HOURS.toMillis(time)
        lastUpdated.text = getString(R.string.lastUpdated, lastDateFormat.format(Date(millis)))
    }

    override fun hideIsRefreshing() {
        swiperefresh.isRefreshing = false
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}