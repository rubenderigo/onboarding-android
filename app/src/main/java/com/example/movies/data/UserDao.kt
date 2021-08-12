package com.example.movies.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

interface UserDao {

    @Query("Select * from user")
    fun getUsers(): Flow<List<User>>

    @Query("SELECT * from user WHERE id = :id")
    fun getItem(id: Int): Flow<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)
}
