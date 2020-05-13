package com.leboncoin.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.leboncoin.data.model.Album
import com.leboncoin.data.repository.AlbumRepository
import com.leboncoin.util.ConnectivityUtil
import com.leboncoin.util.ConnectivityUtil.isAlbumDaoAvailable
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumViewModel(private val albumRepository: AlbumRepository) :
    ViewModel() {
    private val TAG = "ViewModelError"

    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d(TAG, "${exception.cause} handled !")
    }
    private val parenJob = SupervisorJob()
    private val coroutineContext: CoroutineContext get() = parenJob + handler
    private val scope = CoroutineScope(coroutineContext)

    private val _albumDaoList = MutableLiveData<List<Album>>()
    val albumDaoList: LiveData<List<Album>>
        get() = _albumDaoList
    private val _albumList = MutableLiveData<List<Album>>()
    val albumList: LiveData<List<Album>>
        get() = _albumList

    init {
        getAlbum()
        getAllAlbum()
    }

    private fun getAlbum() {
        scope.launch {
            val networkCall = albumRepository.getAlbums()

            withContext(Dispatchers.Main) {
                _albumList.value = networkCall
            }
        }
    }

     fun getAllAlbum() {
        scope.launch {
            val daoAlbumList = albumRepository.getAllAlbums()

            withContext(Dispatchers.Main) {
                _albumDaoList.value = daoAlbumList
                isAlbumDaoAvailable.value = _albumDaoList.value!!.isNotEmpty()
            }
        }
    }

    override
    fun onCleared() {
        super.onCleared()
        coroutineContext.cancel()
    }
}