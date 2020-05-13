package com.leboncoin.data.repository

import com.leboncoin.data.model.Album
import com.leboncoin.data.network.AlbumApi
import com.leboncoin.data.persistence.AlbumDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class AlbumRepository(private val albumApi: AlbumApi, private val dao: AlbumDao) :
    BaseRepository() {

    suspend fun getAlbums() =
        withContext(Dispatchers.Default) {
            val albumResponse = safeApiCall(
                call = { albumApi.getAlbum().await() },
                errorMessage = "Oops something is wrong ..!!"
            )
            albumResponse!!.let { dao.insertList(it) }
            return@withContext albumResponse!!.toMutableList()
        }

    suspend fun getAllAlbums(): List<Album> =
        withContext(Dispatchers.Default) {
            async { dao.findAllAlbums() }
        }.await()


}