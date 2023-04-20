package com.example.test

import com.example.test.ui.dashboard.Bug
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ACNHService {

    @GET("bugs")
    fun getAllBugs(): Call<List<Bug>>

    @GET("bugs/{name}")
    fun getBug(@Path("name") name: String): Call<Bug>
}