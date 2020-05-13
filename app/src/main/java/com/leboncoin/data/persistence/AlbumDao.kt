package com.leboncoin.data.persistence

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.leboncoin.data.model.Album

@Dao
interface AlbumDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(albumList: List<Album>)

    @Query("SELECT count(*) FROM album")
    suspend fun getCount(): Long

    @Query("SELECT * FROM album")
    suspend fun findAllAlbums(): List<Album>
}