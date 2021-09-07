package com.example.movies

import android.app.Application
import com.example.movies.data.UserRoomDatabase

class MoviesApplication : Application() {
    val database: UserRoomDatabase by lazy { UserRoomDatabase.getDatabase(this) }
}