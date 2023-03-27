package com.example.picodiploma.socialapp

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.picodiploma.socialapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserListAdapter
    private lateinit var viewModel: MainViewModel

    companion object {
        private const val TAG = "MainActivity"
        private const val USER_ID = "arif"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Create an instance of MainViewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        supportActionBar?.title = "Github User's Search"

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.recyclerView.addItemDecoration(itemDecoration)

        adapter = UserListAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        // Observe the usersLiveData from MainViewModel
        viewModel.getUsers().observe(this, { users ->
            for (user in users) {
                user.avatarUrl = user.avatarUrl // set the avatarUrl property using the avatar_url property
            }
            adapter.setUserList(users)
            showLoading(false)
        })

        // Show the results for the hardcoded USER_ID when the app starts
        showLoading(true)
        viewModel.getGithubUsers(USER_ID)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)

        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                showLoading(true)
                // Call the getGithubUsers function from MainViewModel
                viewModel.getGithubUsers(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                // Handle text changes
                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                Log.d(TAG, "Search menu item clicked")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
            binding.recyclerView.visibility = View.GONE
        } else {
            binding.progressBar.visibility = View.GONE
            binding.recyclerView.visibility = View.VISIBLE
        }
    }
}
