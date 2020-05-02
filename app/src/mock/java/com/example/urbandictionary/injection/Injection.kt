package com.example.urbandictionary.injection

import android.content.Context
import com.example.urbandictionary.model.network.MockUrbanRestServiceImpl
import com.example.w4d2_mvvm_example.db.AppDatabase
import com.example.w4d2_mvvm_example.model.network.UrbanRepository
import com.example.w4d2_mvvm_example.model.network.UrbanRepositoryImpl
import com.example.w4d2_mvvm_example.model.network.remote.UrbanRestService

class Injection {
    private var userRestService: UrbanRestService? = null
    private var dataBaseInstance: AppDatabase?= null

    fun provideUserRepo(context: Context): UrbanRepository {
        return UrbanRepositoryImpl(provideUrbanRestService(), provideAppDatabase(context))
    }

    private fun provideAppDatabase(context: Context): AppDatabase {
        if (dataBaseInstance == null) {
            dataBaseInstance = AppDatabase.getDatabaseInstance(context)
        }
        return dataBaseInstance!!
    }

    private fun provideUrbanRestService(): UrbanRestService {
        if (userRestService == null) {
            userRestService = MockUrbanRestServiceImpl()
        }
        return userRestService as UrbanRestService
    }
}