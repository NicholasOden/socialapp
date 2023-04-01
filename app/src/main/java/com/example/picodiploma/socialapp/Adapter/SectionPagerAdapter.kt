package com.example.picodiploma.socialapp.Adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

import com.example.picodiploma.socialapp.R

class SectionPagerAdapter(activity: AppCompatActivity, private val fragmentList: List<Fragment>) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun getTabTitle(position: Int): Int {
        // Replace this with a valid string resource ID for each tab
        return when (position) {
            0 -> R.string.tab_text_1
            1 -> R.string.tab_text_2
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}

