package com.example.w4d2_mvvm_example.model.network

import com.example.w4d2_mvvm_example.db.AppDatabase
import com.example.w4d2_mvvm_example.model.network.remote.UrbanRestService
import com.example.w4d2_mvvm_example.model.response.Word
import io.reactivex.Single
import javax.inject.Inject

open class UrbanRepositoryDagger @Inject constructor(private val urbanRestService: UrbanRestService,
                                                     private val appDatabase: AppDatabase)  {

    open fun getDefinitionList(term: String): Single<MutableList<Word>> {
        val word1 = Word(1, "word1", "definition", 1, 1)
        val word2 = Word(2, "word2", "definition2", 2, 2)
        val word3 = Word(3, "word3", "definition3", 3, 3)
        val words = mutableListOf(word1, word2, word3)
        return Single.just(words)
    }
}