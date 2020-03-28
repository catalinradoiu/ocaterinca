package com.ocaterinca.ocaterinca.feature

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ocaterinca.ocaterinca.AvatarCardFragmentBinding
import com.ocaterinca.ocaterinca.GameViewModel
import com.ocaterinca.ocaterinca.R
import com.ocaterinca.ocaterinca.core.model.GameStep
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import pl.aprilapps.easyphotopicker.ChooserType
import pl.aprilapps.easyphotopicker.EasyImage
import pl.aprilapps.easyphotopicker.MediaFile
import pl.aprilapps.easyphotopicker.MediaSource
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class AvatarCardFragment : Fragment() {

    companion object {
        const val REQUEST_PERMISSIONS = 200
    }

    private lateinit var binding: AvatarCardFragmentBinding
    private val avatarCardViewModel: AvatarCardViewModel by viewModel()
    private val parentViewModel: GameViewModel by sharedViewModel()
    private lateinit var easyImage: EasyImage

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_avatar_card, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = avatarCardViewModel
        }
        initImagePicker()
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        val avatarClickListener = View.OnClickListener {
            checkPermissionsToPickFromCamera()
        }
        binding.userAvatar.setOnClickListener(avatarClickListener)
        binding.userAvatarPlaceholder.setOnClickListener(avatarClickListener)
        binding.nextButton.setOnClickListener {
            avatarCardViewModel.uploadImage()
        }
    }

    private fun initImagePicker() {
        easyImage = EasyImage.Builder(requireContext())
            .setChooserType(ChooserType.CAMERA_AND_GALLERY)
            .build()
    }

    private fun initObservers() {
        avatarCardViewModel.avatarUploaded.observe(viewLifecycleOwner, Observer {
            parentViewModel.finishedStep.value = GameStep.AVATAR_UPLOAD
        })
    }


    @AfterPermissionGranted(REQUEST_PERMISSIONS)
    private fun checkPermissionsToPickFromCamera() {
        val perms = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        if (EasyPermissions.hasPermissions(requireContext(), *perms)) {
            pickImage()
        } else {
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.image_storage_and_camera_rationale),
                REQUEST_PERMISSIONS,
                *perms
            )
        }
    }

    private fun pickImage() {
        easyImage.openCameraForImage(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        easyImage.handleActivityResult(
            requestCode,
            resultCode,
            data,
            requireActivity(),
            imagePickCallbacks
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults, this
        )
    }

    private val imagePickCallbacks = object : EasyImage.Callbacks {
        override fun onCanceled(source: MediaSource) {
            Toast.makeText(requireContext(), R.string.user_cancelled, Toast.LENGTH_SHORT).show()
        }

        override fun onImagePickerError(error: Throwable, source: MediaSource) {
            Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
        }

        override fun onMediaFilesPicked(imageFiles: Array<MediaFile>, source: MediaSource) {
            if (imageFiles.isNotEmpty()) {
                avatarCardViewModel.pickedImage(imageFiles[0].file)
            }
        }
    }

}