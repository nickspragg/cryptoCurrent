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

    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun getMarketChart(isRefresh: Boolean) {
//        https://api.blockchain.info/charts/transactions-per-second
//        ?timespan=5weeks&rollingAverage=8hours&format=json
        disposable += marketService
            .fetchMarketPrice()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                { response ->
                    response.values?.run {
                        view.showMarketChart(this)
                    }
                },
                { error ->
                    Timber.e(error)
                }
            )
    }

    override fun getSummaryStats(isRefresh: Boolean) {
        disposable += marketService
            .fetchStats()
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .doFinally { if(isRefresh) view.hideIsRefreshing() }
            .subscribe(
                { response ->
                    response.run {
                        view.showCurrentPrice(marketPriceUsd)
                        view.showTradeVolume(tradeVolumeUsd)
                        view.showLastUpdated(timestamp)
                    }
                },
                { error ->
                    Timber.e(error)
                }
            )
    }

    override fun dispose() {
        disposable.clear()
    }
}