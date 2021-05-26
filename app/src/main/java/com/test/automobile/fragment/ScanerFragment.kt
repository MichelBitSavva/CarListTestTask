package com.test.automobile.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.google.gson.Gson
import com.test.automobile.R
import com.test.automobile.databinding.FragmentScanerBinding
import com.test.automobile.model.qrData.QrData
import com.test.automobile.utils.toEditable
import com.test.automobile.viewModel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

@AndroidEntryPoint
class ScanerFragment : Fragment(R.layout.fragment_scaner) {
    private var _binding: FragmentScanerBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var codeScanner: CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentScanerBinding.bind(view)

        val activity = requireActivity()
        codeScanner = CodeScanner(activity, binding.scannerView)
        var qrData: QrData?
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {
                val answerString = """$it"""
                val gson = Gson()
                qrData = gson.fromJson(answerString, QrData::class.java)
                sharedViewModel.setQrData(qrData!!)
                findNavController().navigateUp()
            }
        }

        codeScanner.errorCallback = ErrorCallback {
            activity.runOnUiThread {
                Toast.makeText(requireContext(), "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}






