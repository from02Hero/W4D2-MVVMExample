package com.example.w4d2_mvvm_example.model.response

data class UrbanResponse(val list: MutableList<Word>)

data class Word(val definition: String, val thumbs_up: Int, val thumbs_down: Int)