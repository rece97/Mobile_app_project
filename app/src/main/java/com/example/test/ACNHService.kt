package com.example.test

import com.example.test.ui.dashboard.Bug
import com.example.test.ui.dashboard.Fish
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ACNHService {

    @GET("bugs")
    fun getAllBugs(): Call<List<Bug>>

    @GET("bugs/{id}")
    fun getBug(@Path("id") id: Int): Call<Bug>

    @GET("fish")
    fun getAllFish(): Call<List<Fish>>

    @GET("fish/{id}")
    fun getFish(@Path("id") id: Int): Call<Fish>
}