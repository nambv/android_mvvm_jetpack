package com.nambv.android_mvvm.ui.view.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nambv.android_mvvm.MainDispatcherRule
import com.nambv.android_mvvm.data.api.ApiService
import com.nambv.android_mvvm.data.repository.UsersRepository
import com.nambv.android_mvvm.domain.getcars.GetCarsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.*
import java.io.IOException

/**
 * Unit tests for the [CarsViewModel].
 */
@ExperimentalCoroutinesApi
class CarsViewModelTest {

    @Mock
    lateinit var apiService: ApiService

    @Mock
    lateinit var repository: UsersRepository

    @Mock
    lateinit var getCarsUseCase: GetCarsUseCase

    @Mock
    lateinit var viewModel: CarsViewModel

    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainDispatcherRule()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = UsersRepository(apiService)
        getCarsUseCase = GetCarsUseCase(repository, coroutineRule.testDispatcher)
        viewModel = CarsViewModel(getCarsUseCase)
    }

    @Test
    fun getCars_isSuccess() = runTest {
        whenever(repository.getUsers()).thenReturn(any())
        viewModel.getCars()
        getCarsUseCase().collect {
            assertEquals(CarsListUiStateReady(cars = it.data), viewModel.state.value)
        }
    }

    @Test
    fun getCars_isFail() = runTest {
        whenever(repository.getUsers()) doAnswer {
            throw IOException()
        }
        viewModel.getCars()
        getCarsUseCase().catch {
            assertEquals(CarsListUiStateError(), viewModel.state.value)
        }
    }

}