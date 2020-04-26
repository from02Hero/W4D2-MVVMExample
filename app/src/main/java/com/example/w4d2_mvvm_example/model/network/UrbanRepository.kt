package com.example.w4d2_mvvm_example.model.network

import com.example.w4d2_mvvm_example.model.response.Word
import io.reactivex.Single

interface UrbanRepository {
    fun getDefinitionList(term: String): Single<MutableList<Word>>
}