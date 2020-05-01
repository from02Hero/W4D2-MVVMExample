package com.example.w4d2_mvvm_example.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w4d2_mvvm_example.R
import com.example.w4d2_mvvm_example.databinding.ActivityMainBinding
import com.example.w4d2_mvvm_example.inject.MyApplication
import com.example.w4d2_mvvm_example.viewmodel.UrbanViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModel: UrbanViewModel
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding?.rvNews?.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.HORIZONTAL, false)
        binding?.let {
            it.viewModel = viewModel
        }
        viewModel.initialState()
        setRecyclerViewLinearLayout()
        wordSearch()
    }

    private fun setRecyclerViewLinearLayout() {
        rvNews.layoutManager = LinearLayoutManager(this)
    }

    private fun wordSearch() {
        viewModel.searchDefinitions(word_search)
    }
}
