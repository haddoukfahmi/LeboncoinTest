package com.leboncoin.ui.albumlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.leboncoin.R
import com.leboncoin.data.model.Album
import com.leboncoin.util.ConnectivityUtil
import com.leboncoin.viewmodel.AlbumViewModel
import kotlinx.android.synthetic.main.fragment_album_list.*
import org.koin.android.ext.android.inject

class AlbumFragment : Fragment(R.layout.fragment_album_list) {

    private val albumViewModel: AlbumViewModel by inject()
    private val adapter = AlbumsAdapter()
    private lateinit var checkConnectivity: ConnectivityUtil.CheckConnectivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkConnectivity = ConnectivityUtil.CheckConnectivity(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        albumViewModel.getAllAlbum()
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()

        checkConnectivity.observe(viewLifecycleOwner, Observer { isConnected ->
            if (isConnected!!) {
                albumViewModel.albumList.observe(viewLifecycleOwner, Observer {
                    setRecyclerView(it!!)
                })
            } else {
                albumViewModel.albumDaoList.observe(viewLifecycleOwner, Observer {
                    setRecyclerView(it!!)
                })
            }

        })

    }

    private fun setRecyclerView(albumList: List<Album>) {
        adapter.submitList(albumList)
    }

    private fun initRecyclerView() {
        album_list!!.layoutManager = LinearLayoutManager(context)
        album_list!!.adapter = adapter
    }
}