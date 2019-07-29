package com.nickspragg.currentmarket

import com.nhaarman.mockito_kotlin.*
import com.nickspragg.currentmarket.model.ChartData
import com.nickspragg.currentmarket.model.StatsData
import com.nickspragg.currentmarket.network.MarketService
import com.nickspragg.currentmarket.rule.RxImmediateSchedulerRule
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

        verify(view).setChartData(values)
        verifyNoMoreInteractions(view)
    }

    @Test
    fun getMarketChart_errorResponse() {
        whenever(marketService.fetchMarketPrice()).thenReturn(Single.error { throw Throwable(httpException) })
        presenter.getMarketChart()

        verifyZeroInteractions(view)
    }

    @Test
    fun getSummaryStats_successResponse_setViewData() {
        whenever(marketService.fetchStats()).thenReturn(Single.just(statsData))
        presenter.getSummaryStats()

        verify(view, times(1)).setCurrentPrice(any())
        verify(view, times(1)).setTradeVolume(any())
        verify(view, times(1)).setLastUpdated(any())
    }

    @Test
    fun getSummaryStats_successResponse_showHidePlaceholder() {
        val inOrder = inOrder(view)
        whenever(marketService.fetchStats()).thenReturn(Single.just(statsData))
        presenter.getSummaryStats()

        inOrder.verify(view, times(1)).showSummaryView(false)
        inOrder.verify(view, times(1)).showPlaceholderSummaryView(true)

        inOrder.verify(view, times(1)).showSummaryView(true)
        inOrder.verify(view, times(1)).showPlaceholderSummaryView(false)
    }

    @Test
    fun getSummaryStats_successResponse_errorNeverShown() {
        whenever(marketService.fetchStats()).thenReturn(Single.just(statsData))
        presenter.getSummaryStats()

        verify(view, never()).showErrorSummaryView(true)
    }

    @Test
    fun getSummaryStats_errorResponse_errorShown() {
        whenever(marketService.fetchStats()).thenReturn(Single.error { throw Throwable(httpException) })
        presenter.getSummaryStats()

        verify(view, times(1)).showErrorSummaryView(true)
    }

    @Test
    fun getSummaryStats_errorResponse_summaryNeverShown() {
        whenever(marketService.fetchStats()).thenReturn(Single.error { throw Throwable(httpException) })
        presenter.getSummaryStats()

        verify(view, never()).showSummaryView(true)
    }

    @Test
    fun getSummaryStats_errorResponse_showHidePlaceholder() {
        val inOrder = inOrder(view)
        whenever(marketService.fetchStats()).thenReturn(Single.error { throw Throwable(httpException) })
        presenter.getSummaryStats()

        inOrder.verify(view, times(1)).showErrorSummaryView(false)
        inOrder.verify(view, times(1)).showPlaceholderSummaryView(true)

        inOrder.verify(view, times(1)).showErrorSummaryView(true)
        inOrder.verify(view, times(1)).showPlaceholderSummaryView(false)
    }
}