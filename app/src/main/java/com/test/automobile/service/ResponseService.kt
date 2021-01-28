package com.test.automobile.service

import com.google.gson.JsonObject
import com.test.automobile.model.carManufacturer.JsonCarManufacturer
import com.test.automobile.model.carModel.JsonCarModel

import retrofit2.Response
import retrofit2.http.*

interface ResponseService {

    @Headers(
        "Content-Type:application/json"
    )

    @GET("5db9630530000095005ee272")
    suspend fun getCarModel(

    ): Response<JsonCarModel>


    @Headers(
        "Content-Type:application/json"
    )

    @POST("5db959e43000005a005ee206")
    suspend fun getCarManufacturer(

    ): Response<JsonCarManufacturer>


}