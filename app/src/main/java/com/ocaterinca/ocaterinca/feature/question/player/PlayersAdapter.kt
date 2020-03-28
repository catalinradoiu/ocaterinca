package com.ocaterinca.ocaterinca.feature.question.player

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ocaterinca.ocaterinca.PlayerItemBinding
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.core.model.Player

private val PLAYER_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Player>() {
    override fun areItemsTheSame(oldItem: Player, newItem: Player) = oldItem.userId == newItem.userId

    override fun areContentsTheSame(oldItem: Player, newItem: Player) = oldItem == newItem
}

class PlayersAdapter : ListAdapter<Player, PlayersAdapter.PlayerViewHolder>(
    PLAYER_DIFF_CALLBACK
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlayerViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_player, parent, false))

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlayerViewHolder(private val binding: PlayerItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.viewModel = PlayerItemViewModel()
        }

        fun bind(player: Player) {
            binding.viewModel?.apply {
                avatarUrl.set(player.image)
            }
        }
    }
}