package com.test.automobile.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.automobile.R
import com.test.automobile.databinding.FragmentAutoMobileBinding
import com.test.automobile.databinding.FragmentPhotoCropBinding
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

val REQUEST_IMAGE = 1

@AndroidEntryPoint
class PhotoCropFragment : Fragment(R.layout.fragment_photo_crop) {
    private var _binding: FragmentPhotoCropBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPhotoCropBinding.bind(view)

        binding.materialButton2.setOnClickListener{
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, REQUEST_IMAGE)
        }

        binding.materialButton3.setOnClickListener{
          val points =  binding.cropImageView.cropPoints

            val cropWindowRect = binding.cropImageView.cropWindowRect
            val width = cropWindowRect.width()

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if( requestCode == REQUEST_IMAGE && resultCode == Activity.RESULT_OK) {
            val imageUri = data?.data
            val imageStream =
                imageUri?.let { requireActivity().contentResolver.openInputStream(it) }
            val selectedImage = BitmapFactory.decodeStream(imageStream)

            binding.cropImageView.setImageUriAsync(imageUri)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}