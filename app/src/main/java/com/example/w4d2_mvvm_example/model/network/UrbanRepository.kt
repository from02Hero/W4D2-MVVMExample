package com.example.w4d2_mvvm_example.model.network

import com.example.w4d2_mvvm_example.model.response.UrbanResponse
import io.reactivex.Single

interface UrbanRepository {
    fun getDefinitionList(term: String): Single<UrbanResponse>
}