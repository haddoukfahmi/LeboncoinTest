package com.leboncoin.di

import com.leboncoin.data.repository.AlbumRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AlbumRepository(get(), get()) }
}