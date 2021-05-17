package com.test.automobile.repository
import com.test.automobile.service.ResponseService
import com.test.automobile.service.RetrofitInstance
import javax.inject.Inject

class AutomobileRepository @Inject constructor(
    private val api: ResponseService
){

    suspend fun getCarManufacturer()  = api.getCarManufacturer()
    suspend fun getCarModel()  = api.getCarModel()
}