package com.example.picodiploma.socialapp

import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val apiService = ApiConfig.getApiService()
    private val usersLiveData = MutableLiveData<List<GithubUser>>()
    private val userLiveData = MutableLiveData<DetailUserResponse>()

    companion object {
        private const val AUTHORIZATION_HEADER = "Authorization"
        private const val AUTHORIZATION_TOKEN = "GITME"
        private const val TAG = "MainViewModel"
    }

    fun getGithubUsers(query: String) {
        val call = apiService.searchUsers("Bearer $AUTHORIZATION_TOKEN", query)
        call.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(call: Call<GithubResponse>, response: Response<GithubResponse>) {
                if (response.isSuccessful) {
                    val users = response.body()?.items
                    usersLiveData.value = users
                } else {
                    Log.e(TAG, "Failed to retrieve Github users")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                Log.e(TAG, "Failed to retrieve Github users", t)
            }
        })
    }

    fun getUsers(): LiveData<List<GithubUser>> {
        return usersLiveData
    }

    fun getDetailUser(username: String) {
        val call = apiService.getDetailUser("Bearer $AUTHORIZATION_TOKEN", username)
        call.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                if (response.isSuccessful) {
                    val user = mapDetailUserToGithubUser(response.body()!!)
                    userLiveData.value = response.body()
                } else {
                    Log.e(TAG, "Failed to retrieve Github user")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                Log.e(TAG, "Failed to retrieve Github user", t)
            }
        })
    }


    fun getUser(): LiveData<DetailUserResponse> {
        return userLiveData
    }

    private fun mapDetailUserToGithubUser(detailUser: DetailUserResponse): GithubUser {
        return GithubUser(
            detailUser.login,
            detailUser.id,
            detailUser.avatarUrl
        )
    }
}
