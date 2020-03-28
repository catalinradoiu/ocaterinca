package com.ocaterinca.ocaterinca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
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
        gameAdapter = GamePagerAdapter(supportFragmentManager)
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
            if (position == gameAdapter.count - 1) {
                gameViewModel.gameStarted.value = true
            }
        }

    }
}
