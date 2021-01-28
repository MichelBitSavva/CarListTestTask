package com.test.automobile.repository
import com.test.automobile.service.RetrofitInstance

class AutomobileRepository {

    suspend fun getCarManufacturer()  = RetrofitInstance.api.getCarManufacturer()
    suspend fun getCarModel()  = RetrofitInstance.api.getCarModel()
}