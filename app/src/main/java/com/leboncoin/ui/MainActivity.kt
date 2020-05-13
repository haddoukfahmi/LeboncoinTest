package com.leboncoin.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.leboncoin.R
import com.leboncoin.ui.albumlist.AlbumFragment
import com.leboncoin.util.ConnectivityUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.scope.lifecycleScope

class MainActivity : AppCompatActivity() {

    private lateinit var checkConnectivity: ConnectivityUtil.CheckConnectivity

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory(lifecycleScope)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkConnectivity = ConnectivityUtil.CheckConnectivity(this)


        GlobalScope.launch(Dispatchers.Main) {
            checkConnectivity.observe(this@MainActivity, Observer {
                if (it!!) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.id_container, AlbumFragment())
                        .commit()
                } else {
                    if (!ConnectivityUtil!!.isAlbumDaoAvailable.value!!)
                        ConnectivityUtil.showNoConnectionDialog(this@MainActivity)
                }
            })
        }
    }
}
