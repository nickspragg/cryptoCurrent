package com.nickspragg.currentmarket

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate
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

class CurrentMarketActivity : Activity(), CurrentMarketContract.View {

    @Inject
    lateinit var presenter: CurrentMarketContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerCurrentMarketComponent
            .builder()
            .coreComponent(CoreInjectHelper.provideCoreComponent(applicationContext))
            .currentMarketModule(CurrentMarketModule(this))
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_market)

        presenter.getMarketChart()
        with(dailyChart){
            setBackgroundColor(Color.WHITE)
            description.setEnabled(true)
            setTouchEnabled(true)
            setDrawGridBackground(true)
            // create marker to display box when values are selected
            isDragEnabled = true
            setScaleEnabled(true)
            setPinchZoom(true)
            axisRight.isEnabled = false
        }
        with(dailyChart.axisLeft){
            setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
            setTextColor(ColorTemplate.getHoloBlue())
            setDrawGridLines(true)
            setGranularityEnabled(true)
            setYOffset(-9f)
            setTextColor(Color.rgb(255, 192, 56))
            setValueFormatter(object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.formatCurrency("USD").toString()
                }
            })
        }
        with(dailyChart.xAxis){
            setPosition(XAxis.XAxisPosition.TOP_INSIDE)
            setTextSize(10f)
            setTextColor(Color.WHITE)
            setDrawAxisLine(false)
            setDrawGridLines(true)
            setTextColor(Color.rgb(255, 192, 56))
            setCenterAxisLabels(true)
            setGranularity(1f) // one hour
            setValueFormatter(object : ValueFormatter() {
                private val mFormat = SimpleDateFormat("dd MMM HH:mm", Locale.ENGLISH)
                override fun getFormattedValue(value: Float): String {
                    val millis = TimeUnit.HOURS.toMillis(value.toLong())
                    return mFormat.format(Date(millis))
                }
            })
        }
    }

    override fun showMarketChart(prices: List<ChartData.PricePoint>) {
        val values = ArrayList<Entry>().apply {
            prices.forEach {
                add(Entry(it.xValue, it.yValue))
            }
        }
        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1")

        // create a data object with the data sets
        val data = LineData(set1).apply {
            setValueTextColor(Color.BLACK)
            setValueTextSize(9f)
        }

        // set data
        dailyChart.setData(data)
        dailyChart.invalidate()
    }

    override fun onDestroy() {
        presenter.dispose()
        super.onDestroy()
    }
}