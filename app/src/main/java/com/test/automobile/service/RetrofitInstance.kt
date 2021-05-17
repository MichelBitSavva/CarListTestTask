package com.test.automobile.service

import android.content.Context
import com.google.gson.GsonBuilder
import com.test.automobile.utils.Constants.Companion.URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitInstance @Inject constructor(){

    fun <Api> buildApi(
        api: Class<Api>
    ): Api {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val gson = GsonBuilder().serializeNulls().create()
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
       return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
            .create(api)

    }
}