package com.example.picodiploma.socialapp.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.picodiploma.socialapp.MainViewModel
import com.example.picodiploma.socialapp.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var nameTextView: TextView
    private lateinit var followersTextView: TextView
    private lateinit var followingTextView: TextView
    private lateinit var avatarImageView: ImageView
    private lateinit var loginTextView: TextView
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout


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
        loginTextView = findViewById(R.id.textView_login)
        followersTextView = findViewById(R.id.textView_followers)
        followingTextView = findViewById(R.id.textView_following)
        avatarImageView = findViewById(R.id.imageView_detail)
        viewPager = findViewById(R.id.view_pager)
        tabLayout = findViewById(R.id.tabs)

        // Get the selected user's login from the intent extra
        val login = intent.getStringExtra(EXTRA_LOGIN)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        if (login != null) {
            viewModel.getDetailUser(login).observe(this, { user ->
                // Update the UI with the user's details
                nameTextView.text = user.name
                loginTextView.text = user.login
                followersTextView.text = "Followers: ${user.followers}"
                followingTextView.text = "Following: ${user.following}"
                Glide.with(this).load(user.avatarUrl).into(avatarImageView)
            })
        }

        val adapter = SectionPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> getString(R.string.tab_text_1)
                1 -> getString(R.string.tab_text_2)
                else -> ""
            }
        }.attach()
    }
}
