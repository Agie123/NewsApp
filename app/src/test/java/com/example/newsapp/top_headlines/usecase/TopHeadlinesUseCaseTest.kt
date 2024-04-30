package com.example.newsapp.top_headlines.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.newsapp.core.feature.top_headlines.domain.usecase.GetTopHeadlinesUseCase
import com.example.newsapp.core.feature.top_headlines.presentation.viewmodel.TopHeadLinesViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopHeadlinesUseCaseTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var topHeadlinesViewModel: TopHeadLinesViewModel
    private lateinit var mockTopHeadlinesRemoteDataSource: TestTopHeadlinesRemoteDataSource
    private lateinit var getTopHeadlinesUseCase: GetTopHeadlinesUseCase

    @Before
    fun setUp() {
        val testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        mockTopHeadlinesRemoteDataSource = TestTopHeadlinesRemoteDataSource()
        getTopHeadlinesUseCase = GetTopHeadlinesUseCase(mockTopHeadlinesRemoteDataSource)
        topHeadlinesViewModel = TopHeadLinesViewModel(getTopHeadlinesUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `use case returns headlines on success`() = runTest {
        // Arrange
        val expectedHeadlines = mockTopHeadlinesRemoteDataSource.getTopHeadlines()

        // Act
        val result = getTopHeadlinesUseCase.invoke()

        // Assert
        assertEquals(expectedHeadlines, result)
        assertTrue(getTopHeadlinesUseCase.hasCache)
    }

    @Test
    fun `fetchTopHeadlines updates LiveData on success`() = runTest {
        // Arrange
        val expectedResponse = mockTopHeadlinesRemoteDataSource.getTopHeadlines()

        // Act
        topHeadlinesViewModel.fetchTopHeadlines()
        advanceUntilIdle()

        // Assert
        val result = topHeadlinesViewModel.topHeadlines.value
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `fetchTopHeadlines should set isLoading to true and then to false`() = runTest {
        // Arrange
        val isLoadingObserver = mock<Observer<Boolean>>()
        topHeadlinesViewModel.isLoading.observeForever(isLoadingObserver)

        // Act
        topHeadlinesViewModel.fetchTopHeadlines()
        advanceUntilIdle()

        // Assert
        verify(isLoadingObserver).onChanged(true)
        verify(isLoadingObserver).onChanged(false)
    }

    @Test
    fun `hasCache should return true when cache is available`() = runTest {
        // Arrange & act
        getTopHeadlinesUseCase.invoke()

        // Assert
        assertTrue(getTopHeadlinesUseCase.hasCache)
    }
}