package com.example.demotuan2chuan

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPageAdapter (private val myContext: Context, fm: FragmentManager, internal var totalTabs: Int) : FragmentPagerAdapter(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getItem(position: Int): Fragment {
        return when (position){
        0-> {
            return FragmentDetails()
        }
            else -> return FragmentList()
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }
}