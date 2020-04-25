package com.example.w4d2_mvvm_example.model.network

import com.example.w4d2_mvvm_example.model.response.UrbanResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UrbanAPi {
    @GET("/define")
    fun getNews(@Query("term") term: String): Single<UrbanResponse>
}