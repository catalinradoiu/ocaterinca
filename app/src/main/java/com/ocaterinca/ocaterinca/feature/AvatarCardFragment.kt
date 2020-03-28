package com.ocaterinca.ocaterinca.feature

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ocaterinca.ocaterinca.AvatarCardFragmentBinding
import com.ocaterinca.ocaterinca.R

class AvatarCardFragment : Fragment() {

    companion object {
        const val REQUEST_PERMISSIONS = 200
    }
    private lateinit var binding: AvatarCardFragmentBinding
    private val viewModel = AvatarCardViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_avatar_card, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.userAvatar.setOnClickListener {

        }

        binding.nextButton.setOnClickListener {

        }
    }
}