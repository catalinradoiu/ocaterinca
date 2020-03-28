package com.ocaterinca.ocaterinca.custom.choosePlayer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.databinding.ViewChoosePlayerBinding
import com.ocaterinca.ocaterinca.utils.getDim
import com.ocaterinca.ocaterinca.utils.resizeAnim

class ChoosePlayerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {
    var binding: ViewChoosePlayerBinding =
        ViewChoosePlayerBinding.inflate(LayoutInflater.from(context), this, true)
}

@BindingAdapter("choosePlayerViewModel")
fun ChoosePlayerView.setViewModel(viewModel: ChoosePlayerViewModel) {
    binding.viewModel = viewModel
}

@BindingAdapter("choosePlayerState")
fun ChoosePlayerView.setState(state: ChoosePlayerViewModel.State?) {
    when (state) {
        ChoosePlayerViewModel.State.Player1Won -> {
            binding.player1Image.resizeAnim(getDim(R.dimen.player_avatar_size_expanded))
            binding.player2Image.resizeAnim(getDim(R.dimen.player_avatar_size))
        }
        ChoosePlayerViewModel.State.Player2Won -> {
            binding.player2Image.resizeAnim(getDim(R.dimen.player_avatar_size_expanded))
            binding.player1Image.resizeAnim(getDim(R.dimen.player_avatar_size))
        }
        ChoosePlayerViewModel.State.PickPlayer, ChoosePlayerViewModel.State.Draft -> {
            binding.player1Image.resizeAnim(getDim(R.dimen.player_avatar_size))
            binding.player2Image.resizeAnim(getDim(R.dimen.player_avatar_size))
        }
    }
}