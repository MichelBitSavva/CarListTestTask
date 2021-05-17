package com.test.automobile.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.automobile.model.carManufacturer.CarManufactures
import com.test.automobile.model.carManufacturer.JsonCarManufacturer
import com.test.automobile.model.carModel.CarModels
import com.test.automobile.model.carModel.JsonCarModel
import com.test.automobile.repository.AutomobileRepository
import com.test.automobile.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class AutomobileViewModel @Inject constructor(
        private val automobileRepository: AutomobileRepository
) : ViewModel() {
    val carManufacturer: MutableLiveData<Resource<JsonCarManufacturer>> = MutableLiveData()
    val carModel: MutableLiveData<Resource<JsonCarModel>> = MutableLiveData()

     var carManufacturerList = ArrayList<CarManufactures>()
     var carModelList = ArrayList<CarModels>()
     lateinit var modelsMap: HashMap<String, List<CarModels>>


    fun getCarManufacturer() = viewModelScope.launch {
        carManufacturer.postValue(Resource.Loading())
        val response = automobileRepository.getCarManufacturer()
        carManufacturer.postValue(handleCarManufacturerResponse(response))
    }

    fun setCarManufacturerList(data: List<CarManufactures>){
        carManufacturerList.addAll(data)
    }

    fun getCarModel() = viewModelScope.launch {
        carModel.postValue(Resource.Loading())
        val response = automobileRepository.getCarModel()
        carModel.postValue(handleCarModelResponse(response))
    }

    fun setCarModelsList(data: List<CarModels>){
        carModelList.addAll(data)
    }


     fun prepareData() {
        modelsMap = HashMap()

        for (i in carManufacturerList) {
            val id = i.id
            val currentModelList =
                    carModelList.filter { model -> model.brand_id == id }
            modelsMap[i.id] = currentModelList
        }


    }




    private fun handleCarManufacturerResponse(response: Response<JsonCarManufacturer>): Resource<JsonCarManufacturer>{
        if(response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.body(), response.errorBody())
    }

    private fun handleCarModelResponse(response: Response<JsonCarModel>): Resource<JsonCarModel>{
        if(response.isSuccessful){
            response.body()?.let {resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.body(), response.errorBody())

    }

}