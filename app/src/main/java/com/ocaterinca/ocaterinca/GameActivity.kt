package com.ocaterinca.ocaterinca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ocaterinca.ocaterinca.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var gameAdapter: GamePagerAdapter
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        gameAdapter = GamePagerAdapter(supportFragmentManager)

        binding.mainPager.adapter = gameAdapter
    }
}
