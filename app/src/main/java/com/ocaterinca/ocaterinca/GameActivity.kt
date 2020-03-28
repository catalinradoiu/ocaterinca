package com.ocaterinca.ocaterinca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.ocaterinca.ocaterinca.core.model.GameStep
import com.ocaterinca.ocaterinca.databinding.ActivityGameBinding
import com.ocaterinca.ocaterinca.utils.Prefs
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var gameAdapter: GamePagerAdapter
    private val gameViewModel: GameViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFirebase()
        binding.mainPager.addOnPageChangeListener(listener)
        binding.mainPager.isPagingEnabled = false
        gameAdapter = GamePagerAdapter(supportFragmentManager)

        gameViewModel.finishedStep.observe(this, Observer {
            when (it) {
                GameStep.AVATAR_UPLOAD -> {
                    binding.mainPager.apply {
                        isPagingEnabled = true
                        currentItem = CODE_ENTER_POSITION
                    }
                }
                GameStep.CODE_ENTER -> {
                    gameAdapter.enableGamePage()
                    binding.mainPager.apply {
                        isPagingEnabled = false
                        currentItem = GAME_POSITION
                    }
                    gameViewModel.gameStarted.value = true
                }
                GameStep.GAME -> Unit
                null -> Unit
            }
        })
    }

    private fun loadFirebase() {
        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    toast(getString(R.string.error_getting_firebase_token))
                    return@OnCompleteListener
                }

                // Get new Instance ID token
                val token = task.result?.token
                Prefs.token = token
                binding.mainPager.adapter = gameAdapter
            })
    }

    private val listener = object : ViewPager.SimpleOnPageChangeListener() {
        override fun onPageSelected(position: Int) {
            if (position == GAME_POSITION) {
                gameViewModel.gameStarted.value = true
            }
        }

    }

    companion object {
        private const val CODE_ENTER_POSITION = 1
        private const val GAME_POSITION = 2
    }
}
