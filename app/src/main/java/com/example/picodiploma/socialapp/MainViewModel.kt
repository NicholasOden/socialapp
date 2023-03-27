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

    fun getGithubUsers(query: String) {
        val call = apiService.searchUsers(query)
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
}


