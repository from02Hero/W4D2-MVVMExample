package com.example.w4d2_mvvm_example.inject

import com.example.w4d2_mvvm_example.model.network.UrbanRepository
import com.example.w4d2_mvvm_example.model.network.UrbanRepositoryImpl
import com.example.w4d2_mvvm_example.model.network.remote.UrbanRestService

class Injection {
    private var userRestService: UrbanRestService? = null
    fun provideUserRepo(): UrbanRepository {
        return UrbanRepositoryImpl(provideUrbanRestService())
    }

    private fun provideUrbanRestService(): UrbanRestService {
        if (userRestService == null) {
            userRestService = UrbanRestService.instance
        }
        return userRestService as UrbanRestService
    }
}