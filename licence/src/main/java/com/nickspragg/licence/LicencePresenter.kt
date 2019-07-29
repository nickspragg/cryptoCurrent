package com.nickspragg.licence

import com.nickspragg.licence.provider.LicenceProvider
import com.nickspragg.licence.viewholder.LicenceViewModel
import javax.inject.Inject

class LicencePresenter @Inject constructor(
    private val view: LicenceContract.View,
    private val licenceProvider: LicenceProvider
) : LicenceContract.Presenter {

    override fun init() {
        val licences = licenceProvider.fetchLicences().map { LicenceViewModel(it) }
        view.showLicences(licences)
    }
}