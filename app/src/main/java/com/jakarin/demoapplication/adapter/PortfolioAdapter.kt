package com.jakarin.demoapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.jakarin.demoapplication.R
import com.jakarin.demoapplication.databinding.ListPortfolioItemBinding
import com.jakarin.demoapplication.model.PortfolioItem
import com.jakarin.demoapplication.utils.ColorUtils
import javax.inject.Inject

class PortfolioAdapter @Inject constructor() :
    ListAdapter<PortfolioItem, PortfolioAdapter.PortfolioViewHolder>(PortfolioDiffCallback()) {

    class PortfolioViewHolder(private val binding: ListPortfolioItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(portfolioItem: PortfolioItem) {
            binding.apply {
                textViewTitle.text = portfolioItem.title
                textViewWithdrawablePoint.text =
                    String.format("%.2f", portfolioItem.withdrawablePoint)
                textViewPendingPoint.apply {
                    when {
                        portfolioItem.pendingPoint > 0 -> {
                            text = String.format("%.2f", portfolioItem.pendingPoint)
                        }
                    }
                }
                textViewChange.apply {
                    text = "(${portfolioItem.change})"
                    when {
                        portfolioItem.change > 0 -> {
                            setTextColor(ColorUtils.changeUp)
                            text = "(+${portfolioItem.change})"
                        }

                        portfolioItem.change < 0 -> {
                            setTextColor(ColorUtils.changeDown)
                        }

                        portfolioItem.change == 0.00 -> {
                            setTextColor(ColorUtils.noChange)
                        }
                    }
                }

                imageViewPlan.load(portfolioItem.imagePlan) {
                    crossfade(true)
                    placeholder(R.drawable.ic_image)
                    error(R.drawable.ic_broken_image)
                    transformations(CircleCropTransformation())
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListPortfolioItemBinding.inflate(inflater, parent, false)
        return PortfolioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        val portfolioItem = getItem(position)
        holder.bind(portfolioItem)
    }

    class PortfolioDiffCallback : DiffUtil.ItemCallback<PortfolioItem>() {
        override fun areItemsTheSame(oldItem: PortfolioItem, newItem: PortfolioItem): Boolean {
            return oldItem.planId == newItem.planId
        }

        override fun areContentsTheSame(oldItem: PortfolioItem, newItem: PortfolioItem): Boolean {
            return oldItem == newItem
        }
    }

}