package com.test.automobile.di

import com.test.automobile.service.ResponseService
import com.test.automobile.service.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesApi(
        retrofitInstance: RetrofitInstance
    ): ResponseService{
        return retrofitInstance.buildApi(ResponseService::class.java)
    }
}