package com.example.picodiploma.socialapp.uiMain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picodiploma.socialapp.GithubUser
import com.example.picodiploma.socialapp.Response.UserDao
import com.example.picodiploma.socialapp.UserDatabase
import kotlinx.coroutines.launch
import android.content.Context


class FavoriteViewModel(context: Context) : ViewModel() {

    private val userDao: UserDao = UserDatabase.getInstance(context).userDao()

    fun getFavoriteUsers(): LiveData<List<GithubUser>> {
        return userDao.getAllUsers()
    }

    fun addFavoriteUser(login: String, avatarUrl: String?) {
        viewModelScope.launch {
            userDao.insertUser(GithubUser(login, 0, avatarUrl ?: "", 0, 0))
        }
    }

    fun deleteFavoriteUser(login: String, avatarUrl: String?) {
        viewModelScope.launch {
            userDao.deleteUser(GithubUser(login, 0, avatarUrl ?: "", 0, 0))
        }
    }
}
