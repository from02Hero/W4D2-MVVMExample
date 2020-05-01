package com.example.w4d2_mvvm_example.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.w4d2_mvvm_example.common.mock
import com.example.w4d2_mvvm_example.common.whenever
import com.example.w4d2_mvvm_example.model.network.UrbanRepository
import com.example.w4d2_mvvm_example.model.response.Word
import com.example.w4d2_mvvm_example.view.WordsAdapter
import io.reactivex.Single
import junit.framework.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val urbanRepository = mock<UrbanRepository>()
    val observerState = mock<Observer<Int>>()

    val viewmodel by lazy { UrbanViewModel(urbanRepository) }

    @Before
    fun initTest() {
        Mockito.reset(urbanRepository, observerState)
    }

    @Test
    fun `init set joke list to empty`() {
        val response = arrayListOf(Word(1, "term", "definition", 1, 1))
        whenever(urbanRepository.getDefinitionList(ArgumentMatchers.anyString()))
            .thenReturn(Single.just(response))

        viewmodel.wordsAdapter = WordsAdapter()
        viewmodel.progressBarVisibilityLiveData.observeForever(observerState)
        viewmodel.getDefinitions("")
        assertEquals(viewmodel.progressBarVisibilityLiveData.value, View.VISIBLE)
    }
}