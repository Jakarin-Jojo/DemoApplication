package com.jakarin.demoapplication.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jakarin.demoapplication.databinding.ListIndicesItemBinding
import com.jakarin.demoapplication.model.Data
import com.jakarin.demoapplication.utils.ColorUtils
import java.util.Locale
import javax.inject.Inject

class IndicesAdapter @Inject constructor() :
    ListAdapter<Data, IndicesAdapter.IndicesViewHolder>(IndicesDiffCallback()) {

    class IndicesViewHolder(private val binding: ListIndicesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.apply {
                textViewShortName.text = data.shortName
                texViewPrice.text = String.format(Locale.getDefault(), "%,.2f", data.price)

                textViewChange.apply {
                    text = if (data.change > 0) {
                        "+${String.format("%.2f", data.change)}"
                    } else {
                        String.format("%.2f", data.change)
                    }
                }

                textViewPercentChange.apply {
                    text = if (data.percentChange > 0) {
                        "+${String.format("%.2f", data.percentChange)}%"
                    } else {
                        "${String.format("%.2f", data.percentChange)}%"
                    }
                }
                val backgroundColor = when {
                    data.percentChange >= 0.5 -> ColorUtils.backgroundChangeUpper
                    data.percentChange in 0.01..0.49 -> ColorUtils.backgroundChangeUp
                    data.percentChange < 0 -> ColorUtils.backgroundChangeDown
                    else -> Color.TRANSPARENT
                }
                root.setBackgroundColor(backgroundColor)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListIndicesItemBinding.inflate(inflater, parent, false)
        return IndicesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IndicesViewHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    class IndicesDiffCallback : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.symbol == newItem.symbol
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}