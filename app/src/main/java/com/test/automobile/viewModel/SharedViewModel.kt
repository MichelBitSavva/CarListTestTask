package com.test.automobile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.automobile.model.carManufacturer.CarManufactures
import com.test.automobile.model.carManufacturer.JsonCarManufacturer
import com.test.automobile.model.carModel.CarModels
import com.test.automobile.model.carModel.JsonCarModel
import com.test.automobile.model.qrData.QrData
import com.test.automobile.repository.AutomobileRepository
import com.test.automobile.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject



class SharedViewModel: ViewModel() {
    private val _qrData:MutableLiveData<QrData> = MutableLiveData()
    val qrData: LiveData<QrData> = _qrData
    fun setQrData(qrData: QrData) {
        _qrData.value = qrData
    }
    fun clearQrData(){
        _qrData.value = null
    }




}