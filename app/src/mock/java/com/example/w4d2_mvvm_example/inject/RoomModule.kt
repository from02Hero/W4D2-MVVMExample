package com.example.w4d2_mvvm_example.inject

import android.app.Application
import androidx.room.Room
import com.example.w4d2_mvvm_example.db.AppDatabase
import com.example.w4d2_mvvm_example.model.local.WordDAO
import com.example.w4d2_mvvm_example.view.WordsAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(mApplication: Application) {
    private val demoDatabase: AppDatabase =
        Room.databaseBuilder(mApplication, AppDatabase::class.java, "demo-db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): AppDatabase {
        return demoDatabase
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: AppDatabase): WordDAO {
        return demoDatabase.wordDao()
    }

    @Provides
    fun providesAdapter(): WordsAdapter {
        return WordsAdapter()
    }

}