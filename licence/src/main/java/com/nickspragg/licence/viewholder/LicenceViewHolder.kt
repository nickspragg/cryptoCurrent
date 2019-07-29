package com.nickspragg.licence.viewholder

import android.view.View
import com.nickspragg.licence.LicenceAdapter
import com.nickspragg.licence.model.LicenceData
import kotlinx.android.synthetic.main.list_item_licence_card.view.*

class LicenceViewHolder(itemView: View) : LicenceAdapter.ViewHolder(itemView) {
    override fun bind(model: LicenceAdapter.ViewModel) {
        if (model is LicenceViewModel) {
            with(itemView) {
                licenceTitle.text = model.licence.title
                licenceDescription.text = model.licence.licence
            }
        }
    }
}

class LicenceViewModel(val licence: LicenceData) : LicenceAdapter.ViewModel