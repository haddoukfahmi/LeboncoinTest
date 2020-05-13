package com.leboncoin.ui.albumlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leboncoin.R
import com.leboncoin.data.model.Album
import com.leboncoin.util.loadPicture
import kotlinx.android.synthetic.main.album_item.view.*

class AlbumsAdapter :
    ListAdapter<Album, AlbumsAdapter.AlbumViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.album_item, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val albumdata = getItem(position)

        holder.Bind(albumdata)
    }


    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun Bind(album: Album) {
            itemView.album_title.text = album.title
            itemView.album_img.loadPicture(album.thumbnailUrl)
        }
    }

}

private class DiffCallback : DiffUtil.ItemCallback<Album>() {

    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }
}

