package com.example.picodiploma.socialapp
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserListAdapter(
    private var userList: List<GithubUser>,
    private val onClickListener: (GithubUser) -> Unit
) : RecyclerView.Adapter<UserListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val loginTextView: TextView = view.findViewById(R.id.tvText)
        val avatarImageView: ImageView = view.findViewById(R.id.ivPicture)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.loginTextView.text = user.login
        Glide.with(holder.avatarImageView.context)
            .load(user.avatarUrl)
            .into(holder.avatarImageView)

        holder.itemView.setOnClickListener {
            onClickListener(user)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setUserList(userList: List<GithubUser>) {
        this.userList = userList
        notifyDataSetChanged()
    }
}
