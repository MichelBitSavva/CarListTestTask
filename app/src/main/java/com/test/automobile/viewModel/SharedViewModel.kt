package com.test.automobile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.automobile.model.qrData.QrData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(): ViewModel() {
    private val _qrData:MutableLiveData<QrData> = MutableLiveData()
    val qrData: LiveData<QrData> = _qrData
    fun setQrData(qrData: QrData) {
        _qrData.value = qrData
    }
    fun clearQrData(){
        _qrData.value = null
    }

}