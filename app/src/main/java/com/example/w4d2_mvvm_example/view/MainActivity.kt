package com.example.w4d2_mvvm_example.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w4d2_mvvm_example.model.response.Word
import com.example.w4d2_mvvm_example.viewmodel.UrbanViewModel
import com.example.w4d2_mvvm_example.viewmodel.UrbanViewModelFactory
import com.example.w4d2_mvvm_example.R
import com.example.w4d2_mvvm_example.databinding.ActivityMainBinding
import com.example.w4d2_mvvm_example.inject.Injection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: UrbanViewModel
    var wordsAdapter: WordsAdapter = WordsAdapter()
    val injection = Injection()
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.rvNews?.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)

        viewModel = ViewModelProvider(
            this,
            UrbanViewModelFactory(injection.provideUserRepo())
        ).get(UrbanViewModel::class.java)

        binding?.let {
            it.viewModel = viewModel
        }

        viewModel.stateLiveData.observe(this, Observer { appState ->
            when (appState) {
                is UrbanViewModel.AppState.LOADING -> displayLoading()
                is UrbanViewModel.AppState.SUCCESS -> displayWords(appState.wordsList)
                is UrbanViewModel.AppState.ERROR -> displayMessage(appState.message)
                else -> displayMessage("Something Went Wrong... Try Again.")
            }
        })

        initRecyclerView()
        wordSearch()
    }

    private fun displayWords(wordsList: MutableList<Word>) {
        // set recycler to eliminate flicker
//        wordsAdapter.update(wordsList)

        // set correct visible element
        progressBar.visibility = View.GONE
        rvNews.visibility = View.VISIBLE
        messageText.visibility = View.GONE
    }

    private fun displayLoading() {
        // set correct visible element
        progressBar.visibility = View.VISIBLE
        rvNews.visibility = View.GONE
        messageText.visibility = View.GONE
    }

    private fun displayMessage(message: String) {
        // set correct visible element
        progressBar.visibility = View.GONE
        rvNews.visibility = View.GONE
        messageText.visibility = View.VISIBLE
        //set message
        messageText.text = message
    }

    private fun initRecyclerView() {
        rvNews.layoutManager = LinearLayoutManager(this)
        rvNews.adapter = wordsAdapter
    }

    private fun wordSearch() {
        viewModel.searchDefinitions(word_search)
    }
}
