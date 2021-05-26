package com.test.automobile.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.test.automobile.R
import com.test.automobile.databinding.FragmentQrCodeBinding
import com.test.automobile.model.qrData.QrData
import com.test.automobile.utils.toEditable
import com.test.automobile.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QrCodeFragment : Fragment(R.layout.fragment_qr_code) {
    private var _binding: FragmentQrCodeBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentQrCodeBinding.bind(view)

        binding.materialButtonScanQR.setOnClickListener {
            findNavController().navigate(R.id.action_qrCodeFragment_to_scanerFragment)
        }
        sharedViewModel.qrData.observe(viewLifecycleOwner, {
            setUpData(it)
        })

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        sharedViewModel.clearQrData()
    }

    private fun setUpData(qrData: QrData){
        val login = qrData.login
        val password = qrData.password
        val server1 = qrData.server1
        val server2 = qrData.server2

        binding.loginTextInputEditText.text = login.toEditable()
        binding.PasswordTextInputEditText.text = password.toEditable()
        binding.server1TextInputEditText.text = server1.toEditable()
        binding.server2TextInputEditText.text = server2.toEditable()
    }

}