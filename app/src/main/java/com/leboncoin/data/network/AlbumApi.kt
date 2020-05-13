package com.leboncoin.data.network

import com.leboncoin.data.model.Album
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface AlbumApi {

    @GET("img/shared/technical-test.json")
    fun getAlbum(): Deferred<Response<List<Album>>>
}