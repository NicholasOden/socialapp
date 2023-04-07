package com.example.picodiploma.socialapp.uiMain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.picodiploma.socialapp.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter = FavoriteAdapter(emptyList())
        binding.recyclerViewFavorite.adapter = adapter

        viewModel.getFavoriteUsers().observe(this, { users ->
            adapter.userList = users
            adapter.notifyDataSetChanged()
        })
    }
}


