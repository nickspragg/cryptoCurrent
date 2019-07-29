package com.nickspragg.currentmarket

import com.nickspragg.currentmarket.network.MarketService
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class CurrentMarketPresenter @Inject constructor(
    private val view: CurrentMarketContract.View,
    private val marketService: MarketService,
    @Named("ioScheduler") private val ioScheduler: Scheduler,
    @Named("mainScheduler") private val mainScheduler: Scheduler
) : CurrentMarketContract.Presenter {

    private enum class Status {
        ERROR, VALID, LOADING
    }

    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun getMarketChart(isRefresh: Boolean) {
//        https://api.blockchain.info/charts/transactions-per-second
//        ?timespan=5weeks&rollingAverage=8hours&format=json
        showChartStatus(Status.LOADING)
        disposable += marketService
            .fetchMarketPrice()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                { response ->
                    response.values?.run {
                        view.setChartData(this)
                        showChartStatus(Status.VALID)
                    }
                },
                { error ->
                    showChartStatus(Status.ERROR)
                    Timber.e(error)
                }
            )
    }

    override fun getSummaryStats(isRefresh: Boolean) {
        showSummaryStatus(Status.LOADING)
        disposable += marketService
            .fetchStats()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doFinally { if (isRefresh) view.hideIsRefreshing() }
            .subscribe(
                { response ->
                    response.run {
                        showSummaryStatus(Status.VALID)
                        view.setCurrentPrice(marketPriceUsd)
                        view.setTradeVolume(tradeVolumeUsd)
                        view.setLastUpdated(timestamp)
                    }
                },
                { error ->
                    showSummaryStatus(Status.ERROR)
                    Timber.e(error)
                }
            )
    }

    override fun dispose() {
        disposable.clear()
    }


    private fun showChartStatus(status: Status) {
        var (showError, showChart, showPlaceholder) = arrayOf(false, false, false)
        when (status) {
            Status.ERROR -> {
                showError = true
            }
            Status.VALID -> {
                showChart = true
            }
            Status.LOADING -> {
                showPlaceholder = true
            }
        }
//        view.showErrorChartView(showError)
//        view.showPlaceholderChartView(showChart)
//        view.showChartView(showPlaceholder)
    }

    private fun showSummaryStatus(status: Status) {
        var (showError, showChart, showPlaceholder) = arrayOf(false, false, false)
        when (status) {
            Status.ERROR -> {
                showError = true
            }
            Status.VALID -> {
                showChart = true
            }
            Status.LOADING -> {
                showPlaceholder = true
            }
        }
        view.showErrorSummaryView(showError)
        view.showSummaryView(showChart)
        view.showPlaceholderSummaryView(showPlaceholder)
    }
}