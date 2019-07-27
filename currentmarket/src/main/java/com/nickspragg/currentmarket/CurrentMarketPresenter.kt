package com.nickspragg.currentmarket

import android.util.Log
import com.nickspragg.currentmarket.network.MarketService
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class CurrentMarketPresenter @Inject constructor(
    private val view: CurrentMarketContract.View,
    private val marketService: MarketService,
    @Named("ioScheduler") private val ioScheduler: Scheduler,
    @Named("mainScheduler") private val mainScheduler: Scheduler
) : CurrentMarketContract.Presenter {

    private var disposable: Disposable? = null

    override fun getMarketChart() {
//        https://api.blockchain.info/charts/transactions-per-second
//        ?timespan=5weeks&rollingAverage=8hours&format=json
        disposable = marketService
            .fetchMarketPrice(
                timespan = "1days"
            )
            .subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe(
                { response ->
                    response.body()?.values?.run {
                        view.showMarketChart(this)
                    }
                },
                { error ->
                    Timber.e(error)
                }
            )
    }

    override fun dispose() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}