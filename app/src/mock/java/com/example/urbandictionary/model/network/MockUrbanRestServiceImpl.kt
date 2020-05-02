package com.example.urbandictionary.model.network

import com.example.w4d2_mvvm_example.model.network.remote.UrbanRestService
import com.example.w4d2_mvvm_example.model.response.UrbanResponse
import com.example.w4d2_mvvm_example.model.response.Word
import io.reactivex.Single

class MockUrbanRestServiceImpl(): UrbanRestService {
    override fun getDefinitions(term: String): Single<UrbanResponse> {
        val word1 = Word(1,"word", "word1", 1, 1)
        val word2 = Word(2, "word", "word2", 2, 2)
        val word3 = Word(3, "word", "word3", 3, 3)
        val words = mutableListOf(word1, word2, word3)
        val response = UrbanResponse(words)
        return Single.just(response)
    }
}