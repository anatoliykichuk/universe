package com.geekbrains.universe.ui.pages.solarsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityPagerBinding
import com.google.android.material.tabs.TabLayoutMediator

class PagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagerBinding

    private val tabTitleIdByPosition = mapOf<Int, Int>(
        0 to R.string.tab_earth_title,
        1 to R.string.tab_mars_title,
        2 to R.string.tab_weather_title
    )

    private val tabIconIdByPosition = mapOf<Int, Int>(
        0 to R.drawable.ic_earth,
        1 to R.drawable.ic_mars,
        2 to R.drawable.ic_system
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = PagerAdapter(this)
        setTabs()
    }

    private fun setTabs() {
        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = getText(tabTitleIdByPosition[position]!!)
            tab.icon = ContextCompat.getDrawable(
                this@PagerActivity, tabIconIdByPosition[position]!!
            )
        }.attach()
    }
}