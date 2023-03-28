package com.example.picodiploma.socialapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var followersTextView: TextView
    private lateinit var followingTextView: TextView
    private lateinit var avatarImageView: ImageView

    private lateinit var viewModel: MainViewModel

    companion object {
        private const val EXTRA_LOGIN = "com.example.picodiploma.socialapp.LOGIN"

        fun newIntent(packageContext: Context, login: String): Intent {
            val intent = Intent(packageContext, DetailActivity::class.java)
            intent.putExtra(EXTRA_LOGIN, login)
            return intent
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        nameTextView = findViewById(R.id.textView_name)
        followersTextView = findViewById(R.id.textView_followers)
        followingTextView = findViewById(R.id.textView_following)
        avatarImageView = findViewById(R.id.imageView_detail)

        // Get the selected user's login from the intent extra
        val login = intent.getStringExtra("login")

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        if (login != null) {
            viewModel.getDetailUser(login)
        }

        viewModel.getUser().observe(this, { user ->
            // Update the UI with the user's details
            nameTextView.text = user.name
            Glide.with(this).load(user.avatarUrl).into(avatarImageView)
        })
    }
}
