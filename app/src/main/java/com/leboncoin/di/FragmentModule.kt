package com.leboncoin.di


import com.leboncoin.ui.MainActivity
import com.leboncoin.ui.albumlist.AlbumFragment
import org.koin.androidx.fragment.dsl.fragment
import org.koin.dsl.module

val fragmentModule = module {
    scope<MainActivity> {
        fragment { AlbumFragment() }
    }
}