package com.example.picodiploma.socialapp.uiMain

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.picodiploma.socialapp.GithubUser
import com.example.picodiploma.socialapp.databinding.ListItemUserBinding
import com.example.picodiploma.socialapp.uiDetail.DetailActivity

class FavoriteAdapter(internal var userList: List<GithubUser>) :
    RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ListItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.binding.tvText.text = user.login
        Glide.with(holder.binding.root).load(user.avatarUrl).into(holder.binding.ivPicture)

        holder.itemView.setOnClickListener {
            val intent = DetailActivity.newIntent(it.context, user.login)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
