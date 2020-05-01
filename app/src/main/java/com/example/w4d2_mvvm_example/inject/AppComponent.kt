package com.example.w4d2_mvvm_example.inject

import android.app.Application
import com.example.w4d2_mvvm_example.db.AppDatabase
import com.example.w4d2_mvvm_example.model.local.WordDAO
import com.example.w4d2_mvvm_example.model.network.UrbanRepositoryDagger
import com.example.w4d2_mvvm_example.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, AppModule::class, RoomModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)

    fun wordDAO(): WordDAO

    fun demoDatabase(): AppDatabase

    fun urbanRepository(): UrbanRepositoryDagger

    fun application(): Application
}