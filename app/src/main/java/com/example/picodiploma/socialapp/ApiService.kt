package com.example.picodiploma.socialapp

import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUsers(
        @Header("GITME") authorizationHeader: String,
        @Query("q") username: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Header("Authorization") authHeader: String,
        @Path("username") username: String
    ): Call<DetailUserResponse>


}
