package com.example.nasaimages.main

import com.example.nasaimages.networking.NasaFetcherService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock


internal class MainViewModelTest {
    @Test
    fun `Given a MainViewModel, When initialize, Then isLoading`() = runTest {
        val viewModel = MainViewModel(mock())
        assertEquals(viewModel.state.first(), MainViewModel.MainUiState(isLoading = true))
    }
}