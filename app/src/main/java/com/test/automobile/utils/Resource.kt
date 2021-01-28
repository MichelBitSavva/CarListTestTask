package com.test.automobile.utils

import okhttp3.ResponseBody

sealed  class Resource<T>(
    val data: T? = null,
    val message: ResponseBody? = null
){
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(data: T? = null, message: ResponseBody?): Resource<T>(data, message)
    class Loading<T>():Resource<T>()
}