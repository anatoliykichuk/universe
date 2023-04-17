package com.geekbrains.universe.ui.pages.solarsystem

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityPagerBinding

class PagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.pager.adapter = PagerAdapter(this)
    }
}