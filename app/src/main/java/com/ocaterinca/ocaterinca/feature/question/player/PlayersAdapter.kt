package com.ocaterinca.ocaterinca.feature.question.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocaterinca.ocaterinca.PlayerItemBinding
import com.ocaterinca.ocaterinca.R

private val PLAYER_DIFF_CALLBACK = object : DiffUtil.ItemCallback<PlayerItemViewModel>() {
    override fun areItemsTheSame(oldItem: PlayerItemViewModel, newItem: PlayerItemViewModel) = oldItem.avatarUrl == newItem.avatarUrl

    override fun areContentsTheSame(oldItem: PlayerItemViewModel, newItem: PlayerItemViewModel) = oldItem == newItem
}

class PlayersAdapter : ListAdapter<PlayerItemViewModel, PlayersAdapter.PlayerViewHolder>(
    PLAYER_DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlayerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_player, parent, false))

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.viewModel = item
        holder.binding.executePendingBindings()
    }

    class PlayerViewHolder(val binding: PlayerItemBinding) : RecyclerView.ViewHolder(binding.root)
}