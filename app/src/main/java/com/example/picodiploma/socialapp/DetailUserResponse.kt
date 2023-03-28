package com.example.picodiploma.socialapp

import com.google.gson.annotations.SerializedName

data class DetailUserResponse(
	val id: Int,
	val login: String,
	@SerializedName("avatar_url") val avatarUrl: String,
	val name: String?,
	val company: String?,
	val blog: String?,
	val location: String?,
	val email: String?,
	val hireable: Boolean?,
	val bio: String?,
	@SerializedName("public_repos") val publicRepos: Int,
	@SerializedName("public_gists") val publicGists: Int,
	val followers: Int,
	val following: Int,
	@SerializedName("created_at") val createdAt: String,
	@SerializedName("updated_at") val updatedAt: String
)


