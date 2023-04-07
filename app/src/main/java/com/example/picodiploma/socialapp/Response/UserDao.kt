package com.example.picodiploma.socialapp.Response

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.picodiploma.socialapp.GithubUser

@Dao
interface UserDao {
    @Query("SELECT * FROM GithubUser")
    fun getAllUsers(): LiveData<List<GithubUser>>

    @Query("SELECT * FROM GithubUser WHERE login = :login")
    fun getUserByLogin(login: String): LiveData<GithubUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: GithubUser): Long

    @Delete
    suspend fun deleteUser(user: GithubUser)
}





