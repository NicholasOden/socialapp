package com.example.picodiploma.socialapp

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {
    @GET("search/users")
    fun searchUsers(@Query("q") username: String): Call<GithubResponse>
}

/*interface ApiService {
    @GET("search/users")
    fun searchUsers(@Query("q") username: String): Call<UserSearchResponse>
}

data class UserSearchResponse(val items: List<GithubResponse>)*/