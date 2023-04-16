package com.geekbrains.universe.ui.pages.solarsystem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityPagerBinding

class PagerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        binding = ActivityPagerBinding.inflate(layoutInflater)
    }
}