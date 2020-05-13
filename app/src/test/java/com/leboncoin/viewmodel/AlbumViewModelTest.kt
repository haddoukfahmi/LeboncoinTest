package com.leboncoin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.leboncoin.data.model.Album
import com.leboncoin.data.repository.AlbumRepository
import com.leboncoin.util.TestCoroutineRule
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var albumRepository: AlbumRepository

    @Mock
    private lateinit var apiUsersObserver: Observer<List<Album>>

    private var successResult = mutableListOf<Album>()

    @Before
    fun setup() {
        albumRepository = mock()
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnAlbumList() {

        testCoroutineRule.runBlockingTest {
            doReturn(listOf<Album>())
                .`when`(albumRepository)
                .getAlbums()
            val viewModel = AlbumViewModel(albumRepository)
            viewModel.albumList.observeForever(apiUsersObserver)
            verify(albumRepository).getAlbums()
            verify(apiUsersObserver).onChanged(successResult)
            viewModel.albumList.removeObserver(apiUsersObserver)
        }
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnGetAlbumFromDao() {
        testCoroutineRule.runBlockingTest {
            doReturn(emptyList<Album>())
                .`when`(albumRepository)
                .getAlbums()
            val viewModel = AlbumViewModel(albumRepository)
            viewModel.albumList.observeForever(apiUsersObserver)
            viewModel.albumDaoList.observeForever(apiUsersObserver)
            verify(albumRepository).getAllAlbums()
            verify(apiUsersObserver).onChanged(successResult)

            viewModel.albumList.removeObserver(apiUsersObserver)
            viewModel.albumDaoList.removeObserver(apiUsersObserver)
        }
    }

}