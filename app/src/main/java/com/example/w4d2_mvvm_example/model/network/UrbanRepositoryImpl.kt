package com.example.w4d2_mvvm_example.model.network

import com.example.w4d2_mvvm_example.model.network.remote.UrbanRestService
import com.example.w4d2_mvvm_example.model.response.UrbanResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class UrbanRepositoryImpl(private val urbanRestService: UrbanRestService) : UrbanRepository {
    override fun getDefinitionList(term: String): Single<UrbanResponse> {
        return urbanRestService
            .getDefinitions(term)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}