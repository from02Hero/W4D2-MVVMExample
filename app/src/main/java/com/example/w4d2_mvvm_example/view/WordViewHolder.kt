package com.example.w4d2_mvvm_example.view

import androidx.recyclerview.widget.RecyclerView
import com.example.w4d2_mvvm_example.databinding.WordItemBinding
import com.example.w4d2_mvvm_example.model.response.Word
import com.example.w4d2_mvvm_example.viewmodel.WordViewModel

class WordViewHolder(private val binding: WordItemBinding) :  RecyclerView.ViewHolder(binding.root) {
    private val wordViewModel = WordViewModel()

    fun bindItem(word: Word) {
        wordViewModel.bind(word)
        binding.viewModel = wordViewModel
    }
}