package com.ocaterinca.ocaterinca

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.ocaterinca.ocaterinca.databinding.ActivityGameBinding
import com.ocaterinca.ocaterinca.utils.Prefs
import org.jetbrains.anko.toast
import timber.log.Timber

class GameActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var gameAdapter: GamePagerAdapter
    private lateinit var viewModel: GameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFirebase()
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
                Timber.e(token)
                binding.mainPager.adapter = gameAdapter
            })
    }
}
