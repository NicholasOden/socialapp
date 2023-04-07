package com.example.picodiploma.socialapp
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import androidx.room.PrimaryKey

data class GithubResponse(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GithubUser>
)
@Entity
data class GithubUser(
    @PrimaryKey val login: String,
    val id: Int,
    @SerializedName("avatar_url") var avatarUrl: String,
    val followers: Int,
    val following: Int
)

