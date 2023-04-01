package com.example.picodiploma.socialapp.uiDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.picodiploma.socialapp.ApiSettings.ApiConfig
import com.example.picodiploma.socialapp.GithubUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowersViewModel : ViewModel() {

    private val _followers = MutableLiveData<List<GithubUser>>()
    val followers: LiveData<List<GithubUser>> = _followers

    fun loadFollowers(login: String) {
        val apiService = ApiConfig.getApiService()
        apiService.getFollowers(login).enqueue(object : Callback<List<GithubUser>> {
            override fun onResponse(
                call: Call<List<GithubUser>>,
                response: Response<List<GithubUser>>
            ) {
                if (response.isSuccessful) {
                    _followers.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<GithubUser>>, t: Throwable) {
                // Handle failure here
            }
        })
    }
}
