package com.example.w4d2_mvvm_example.viewmodel

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.w4d2_mvvm_example.common.mock
import com.example.w4d2_mvvm_example.common.whenever
import com.example.w4d2_mvvm_example.model.network.UrbanRepository
import com.example.w4d2_mvvm_example.model.response.Word
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import java.net.UnknownHostException

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
    val observerList = mock<Observer<MutableList<Word>>>()

    val viewmodel by lazy { UrbanViewModel(urbanRepository) }

    @Before
    fun initTest() {
        Mockito.reset(urbanRepository, observerState)
    }

    @Test
    fun `defineTermThatExists_getDefinitions_getDefinitionsList`() {
        val response = arrayListOf(Word(1, "term", "definition", 1, 1))
        whenever(urbanRepository.getDefinitionList(ArgumentMatchers.anyString()))
            .thenReturn(Single.just(response))

        viewmodel.listVisibilityLiveData.observeForever(observerState)
        viewmodel.stateLiveData.observeForever(observerList)

        viewmodel.getDefinitions("term")
        assertEquals(viewmodel.listVisibilityLiveData.value, View.VISIBLE)
        assertEquals(viewmodel.stateLiveData.value?.size, 1)
    }

    @Test
    fun `defineTermThatDoesntExits_getDefinitions_getEmptyDefinitionsList`() {
        val response:ArrayList<Word> = arrayListOf()
        whenever(urbanRepository.getDefinitionList(ArgumentMatchers.anyString()))
            .thenReturn(Single.just(response))

        viewmodel.listVisibilityLiveData.observeForever(observerState)
        viewmodel.progressBarVisibilityLiveData.observeForever(observerState)
        viewmodel.errorVisibilityLiveData.observeForever(observerState)
        viewmodel.stateLiveData.observeForever(observerList)

        viewmodel.getDefinitions("")

        assertEquals(viewmodel.listVisibilityLiveData.value, View.GONE)
        assertEquals(viewmodel.progressBarVisibilityLiveData.value, View.GONE)
        assertEquals(viewmodel.errorVisibilityLiveData.value, View.VISIBLE)
        assertEquals(viewmodel.errorMessagLiveData.value,"No Definitions Retrieved")
        assertEquals(viewmodel.stateLiveData.value, null)
    }

    @Test
    fun `defineTerm_getDefinitionsUnknownHostException_getErrorNoInternetConnection`() {
        whenever(urbanRepository.getDefinitionList(ArgumentMatchers.anyString()))
            .thenReturn(Single.error(UnknownHostException()))

        viewmodel.listVisibilityLiveData.observeForever(observerState)
        viewmodel.progressBarVisibilityLiveData.observeForever(observerState)
        viewmodel.errorVisibilityLiveData.observeForever(observerState)
        viewmodel.stateLiveData.observeForever(observerList)

        viewmodel.getDefinitions("")

        assertEquals(viewmodel.listVisibilityLiveData.value, View.GONE)
        assertEquals(viewmodel.progressBarVisibilityLiveData.value, View.GONE)
        assertEquals(viewmodel.errorVisibilityLiveData.value, View.VISIBLE)
        assertEquals(viewmodel.errorMessagLiveData.value,"No Internet Connection")
        assertEquals(viewmodel.stateLiveData.value, null)
    }
}