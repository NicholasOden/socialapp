package com.example.picodiploma.socialapp
import com.google.gson.annotations.SerializedName

data class GitHubUser(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
    @SerializedName("url") val url: String
)
