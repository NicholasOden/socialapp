package com.example.picodiploma.socialapp.uiDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide


import com.example.picodiploma.socialapp.databinding.ActivityDetailBinding
import com.example.picodiploma.socialapp.uiMain.MainViewModel
import com.example.picodiploma.socialapp.Adapter.SectionPagerAdapter
import com.example.picodiploma.socialapp.GithubUser
import com.example.picodiploma.socialapp.R
import com.example.picodiploma.socialapp.uiMain.FavoriteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel

    companion object {
        private const val EXTRA_LOGIN = "com.example.picodiploma.socialapp.LOGIN"

        fun newIntent(packageContext: Context, login: String): Intent {
            val intent = Intent(packageContext, DetailActivity::class.java)
            intent.putExtra(EXTRA_LOGIN, login)
            return intent
        }
    }

    class DetailActivity : AppCompatActivity() {

        private lateinit var binding: ActivityDetailBinding
        private lateinit var viewModel: MainViewModel
        private lateinit var favoriteViewModel: FavoriteViewModel

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

            binding = ActivityDetailBinding.inflate(layoutInflater)
            setContentView(binding.root)

            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
            favoriteViewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

            // Get the selected user's login from the intent extra
            val login = intent.getStringExtra(EXTRA_LOGIN)

            // Set the ProgressBar to visible by default
            binding.progressBarDetailUp.visibility = View.VISIBLE

            viewModel.getDetailUser(login ?: "").observe(this, { user ->
                // Update the UI with the user's details
                binding.textViewName.text = user.name
                binding.textViewLogin.text = user.login
                binding.textViewFollowers.text = "Followers: ${user.followers}"
                binding.textViewFollowing.text = "Following: ${user.following}"
                Glide.with(this).load(user.avatarUrl).into(binding.imageViewDetail)
                binding.progressBarDetailUp.visibility = View.GONE

                // Set the click listener on the FloatingActionButton to add the user as a favorite
                val fabFavorite = findViewById<FloatingActionButton>(R.id.fab_favorite)
                fabFavorite.setOnClickListener {
                    favoriteViewModel.addFavoriteUser(login ?: "", user.avatarUrl)
                    Snackbar.make(it, "Added to favorites", Snackbar.LENGTH_SHORT).show()
                }
            })

            val fragmentList: MutableList<Fragment> = mutableListOf()
            fragmentList.add(
                FollowersFragment.newInstance(
                    login ?: ""
                )
            ) // pass empty string if login is null
            fragmentList.add(FollowingFragment.newInstance(login ?: ""))

            val adapter = SectionPagerAdapter(this, fragmentList)
            binding.viewPager.adapter = adapter

            TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
                tab.text = getString(adapter.getTabTitle(position))
            }.attach()

        }

    }
}

