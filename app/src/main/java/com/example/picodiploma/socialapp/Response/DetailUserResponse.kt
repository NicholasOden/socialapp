package com.example.picodiploma.socialapp.Response

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
	val login: String,
	val id: Int,
	@SerializedName("avatar_url") var avatarUrl: String,
	val name: String?,
	@SerializedName("public_repos") val publicRepos: Int,
	val followers: Int,
	val following: Int
)



