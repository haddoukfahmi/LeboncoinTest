package com.leboncoin.di

import com.leboncoin.data.persistence.AppDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseMdule = module {
    single { AppDataBase.getInstance(androidContext()) }
    single { get<AppDataBase>().getAlbumDao() }
}