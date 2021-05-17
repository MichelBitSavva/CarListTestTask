package com.test.automobile.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.test.automobile.R
import com.test.automobile.databinding.FragmentAutoMobileBinding
import com.test.automobile.databinding.FragmentMainMenuBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainMenuFragment : Fragment(R.layout.fragment_main_menu) {
    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainMenuBinding.bind(view)

        binding.materialButtonCarList.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_autoMobileFragment)
        }
        binding.materialButtonPhotoCrop.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_photoCropFragment)
        }
        binding.materialButtonCameraQR.setOnClickListener {
            findNavController().navigate(R.id.action_mainMenuFragment_to_qrCodeFragment)
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





}