package com.geekbrains.universe.ui.pages.solarsystem

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragmentsByPosition = mapOf<Int, Fragment>(
        0 to EarthFragment(),
        1 to MarsFragment(),
        2 to WeatherFragment(),
    )

    override fun getItemCount(): Int {
        return fragmentsByPosition.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentsByPosition[position]!!
    }

//    override fun getPageTitle(position: Int): CharSequence? {
//
//    }
}