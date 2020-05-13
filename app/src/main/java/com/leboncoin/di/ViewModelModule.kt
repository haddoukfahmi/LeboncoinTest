package com.leboncoin.di

import com.leboncoin.viewmodel.AlbumViewModel
import org.koin.dsl.module

val viewmodelModule = module {
    single { AlbumViewModel(get()) }
}