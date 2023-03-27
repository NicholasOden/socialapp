package com.example.picodiploma.socialapp
import com.google.gson.annotations.SerializedName

data class GithubResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GithubUser>
)

data class GithubUser(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url") var avatarUrl: String
)

