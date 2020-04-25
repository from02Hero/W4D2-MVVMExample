package com.example.w4d2_mvvm_example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.w4d2_mvvm_example.model.network.UrbanRepository

class UrbanViewModelFactory(private val urbanRepository: UrbanRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UrbanViewModel(urbanRepository) as T
    }
}