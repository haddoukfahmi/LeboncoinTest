package com.leboncoin

import android.app.Application
import com.leboncoin.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class LeboncoinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@LeboncoinApp)
            modules(
                fragmentModule,
                networkModule,
                repositoryModule,
                viewmodelModule,
                roomDatabaseMdule
            )
        }
    }
}