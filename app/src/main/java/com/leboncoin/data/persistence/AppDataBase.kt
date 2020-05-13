package com.leboncoin.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.leboncoin.data.model.Album
import com.leboncoin.data.persistence.AppDataBase.Companion.DB_VERSION

@Database(entities = [Album::class], version = DB_VERSION, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getAlbumDao(): AlbumDao

    companion object {
        const val DB_VERSION = 2
        private const val DB_NAME = "leboncoin.db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: build(context).also { INSTANCE = it }
            }

        private fun build(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}