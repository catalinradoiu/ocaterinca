package com.ocaterinca.ocaterinca.custom.choosePlayer

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.ocaterinca.ocaterinca.databinding.ViewChoosePlayerBinding

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
            binding.player1Image
                .animate().scaleX(1.5f).scaleY(1.5f)
                .setDuration(1000)
                .start()
        }
        ChoosePlayerViewModel.State.Player2Won -> {
            binding.player2Image
                .animate().scaleX(1.5f).scaleY(1.5f)
                .setDuration(1000)
                .start()
        }
        ChoosePlayerViewModel.State.PickPlayer -> {
            binding.player1Image
                .animate().scaleX(1f).scaleY(1f)
                .setDuration(1000)
                .start()
            binding.player1Image
                .animate().scaleX(1f).scaleY(1f)
                .setDuration(1000)
                .start()
        }
    }
}