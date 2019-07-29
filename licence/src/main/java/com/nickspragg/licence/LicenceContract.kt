package com.nickspragg.licence

import com.nickspragg.licence.viewholder.LicenceViewModel

interface LicenceContract {
    interface View {
        fun showLicences(licences: List<LicenceViewModel>)
    }

    interface Presenter {
        fun init()
    }
}