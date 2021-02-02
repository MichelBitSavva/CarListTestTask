package com.test.automobile.fragment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.test.automobile.R
import com.test.automobile.adapters.ExpandableListViewAutomobileAdapter
import com.test.automobile.databinding.FragmentAutoMobileBinding
import com.test.automobile.model.carManufacturer.CarManufactures
import com.test.automobile.model.carModel.CarModels
import com.test.automobile.model.error.ErrorJson
import com.test.automobile.repository.AutomobileRepository
import com.test.automobile.utils.Dialogs
import com.test.automobile.utils.Dialogs.Companion.showAlertDialogNoInternet
import com.test.automobile.utils.Resource
import com.test.automobile.utils.hide
import com.test.automobile.utils.show
import com.test.automobile.viewModel.AutomobileViewModel
import com.test.automobile.viewModelFactory.AutomobileViewModelProviderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AutoMobileFragment : Fragment(R.layout.fragment_auto_mobile) {


    private var _binding: FragmentAutoMobileBinding? = null
    private val binding get() = _binding!!
    private lateinit var automobileViewModel: AutomobileViewModel
    private lateinit var expandableListViewAutomobileAdapter: ExpandableListViewAutomobileAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val automobileRepository = AutomobileRepository()
        val automobileViewModelProviderFactory = AutomobileViewModelProviderFactory(
                automobileRepository
        )
        automobileViewModel = ViewModelProvider(
                this,
                automobileViewModelProviderFactory
        ).get(AutomobileViewModel::class.java)

    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAutoMobileBinding.bind(view)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onResume() {
        super.onResume()
        Log.d("TAG", "onResume: onResume")
        if(isNetworkAvailable(context)){
            if(automobileViewModel.carManufacturer.value == null){
                automobileViewModel.getCarManufacturer()
                automobileViewModel.getCarModel()
            }else{
                GlobalScope.launch(Dispatchers.IO) {
                    automobileViewModel.prepareData()
                    withContext(Dispatchers.Main) {
                        setAdapter()
                    }

                }
            }

        }else{
            showAlertDialogNoInternet(requireContext(), view)
        }

        automobileViewModel.carManufacturer.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.hide()
                    response.data?.let { manufacturerJson ->
                        automobileViewModel.setCarManufacturerList(manufacturerJson.data)
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.hide()
                    response.message?.let {
                        context?.let { Dialogs.serverErrorShowDialog(it) }
                    }
                }

                is Resource.Loading -> {
                    binding.progressBar.show()
                }
            }
        })

        automobileViewModel.carModel.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.hide()
                    response.data?.let { modelJson ->
                        automobileViewModel.setCarModelsList(modelJson.data)
                        GlobalScope.launch(Dispatchers.IO) {
                            automobileViewModel.prepareData()
                            withContext(Dispatchers.Main) {
                                setAdapter()
                            }

                        }

                    }


                }
                is Resource.Error -> {
                    binding.progressBar.hide()
                    response.message?.let {
                        context?.let { Dialogs.serverErrorShowDialog(it) }
                    }
                }

                is Resource.Loading -> {
                    binding.progressBar.show()
                }
            }
        })

    }

    override fun onPause() {
        super.onPause()
        Log.d("TAG", "onPause: PAUSE")
        automobileViewModel.carModelList.clear()
        automobileViewModel.carManufacturerList.clear()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setAdapter() {
        expandableListViewAutomobileAdapter = ExpandableListViewAutomobileAdapter(
                requireContext(),
                automobileViewModel.carManufacturerList,
                automobileViewModel.modelsMap
        )
        binding.autoExpandableListView.setAdapter(
                expandableListViewAutomobileAdapter
        )
        for (i in 0 until expandableListViewAutomobileAdapter.groupCount) binding.autoExpandableListView.expandGroup(i)
    }

    private fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
                context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities =
                        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        return true
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                        return true
                    }
                }
            } else {
                try {
                    val activeNetworkInfo = connectivityManager.activeNetworkInfo
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                        return true
                    }
                } catch (e: Exception) {
                }
            }
        }
        return false
    }


}