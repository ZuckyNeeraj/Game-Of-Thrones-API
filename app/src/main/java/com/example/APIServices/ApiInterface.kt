package com.example.APIServices

import com.example.data.DataModelItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("Characters")
    fun getData(): Call<List<DataModelItem>>

}