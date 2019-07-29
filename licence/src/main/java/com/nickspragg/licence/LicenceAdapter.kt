package com.nickspragg.licence

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nickspragg.licence.viewholder.LicenceViewHolder

class LicenceAdapter : ListAdapter<LicenceAdapter.ViewModel, LicenceAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LicenceViewHolder(
            inflater.inflate(
                R.layout.list_item_licence_card,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffCallback : DiffUtil.ItemCallback<ViewModel>() {
        override fun areItemsTheSame(oldItem: ViewModel, newItem: ViewModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ViewModel, newItem: ViewModel): Boolean {
            return oldItem == newItem
        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract fun bind(model: ViewModel)
    }

    interface ViewModel {
        override fun equals(other: Any?): Boolean
    }
}
