package com.nickspragg.currentmarket

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyZeroInteractions
import com.nhaarman.mockito_kotlin.whenever
import com.nickspragg.currentmarket.model.ChartData
import com.nickspragg.currentmarket.model.StatsData
import com.nickspragg.currentmarket.network.MarketService
import com.nickspragg.currentmarket.rule.RxImmediateSchedulerRule
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class CurrentMarketPresenterTest {

    companion object {
        @ClassRule
        @JvmField
        var schedulers = RxImmediateSchedulerRule()
    }

    private lateinit var presenter: CurrentMarketPresenter
    @Mock
    private lateinit var marketService: MarketService
    @Mock
    private lateinit var ioScheduler: Scheduler
    @Mock
    private lateinit var mainScheduler: Scheduler
    @Mock
    private lateinit var view: CurrentMarketContract.View
    @Mock
    private lateinit var chartData: ChartData
    @Mock
    private lateinit var values: List<ChartData.PricePoint>
    @Mock
    private lateinit var statsData: StatsData
    @Mock
    private lateinit var httpException: HttpException

    @Before
    fun setup() {
        presenter = CurrentMarketPresenter(view, marketService, Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Test
    fun getMarketChart_successResponse() {
        whenever(marketService.fetchMarketPrice()).thenReturn(Single.just(chartData))
        whenever(chartData.values).thenReturn(values)
        presenter.getMarketChart()
        verify(view).showMarketChart(values)
    }

    @Test
    fun getMarketChart_errorResponse() {
        whenever(marketService.fetchMarketPrice()).thenReturn(Single.error { throw Throwable(httpException) })
        presenter.getMarketChart()
        verifyZeroInteractions(view)
    }

    @Test
    fun getSummaryStats_successResponse() {
        whenever(marketService.fetchStats()).thenReturn(Single.just(statsData))
        presenter.getSummaryStats()
        verify(view).showCurrentPrice(any())
        verify(view).showTradeVolume(any())
        verify(view).showLastUpdated(any())
    }

    @Test
    fun getSummaryStats_errorResponse() {
        whenever(marketService.fetchStats()).thenReturn(Single.error { throw Throwable(httpException) })
        presenter.getSummaryStats()
        verifyZeroInteractions(view)
    }
}