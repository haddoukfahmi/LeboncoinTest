package com.leboncoin.di


import com.leboncoin.data.network.AlbumApi
import com.leboncoin.data.network.RetrofitClient
import org.koin.dsl.module
import retrofit2.Retrofit

val retrofit: Retrofit = RetrofitClient.CreateNetworkClient()
private val albumApi: AlbumApi = retrofit.create(AlbumApi::class.java)

val networkModule = module {
    single { albumApi }
}