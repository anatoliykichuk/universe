package com.geekbrains.universe.ui.pages.animations

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityAnimationsBinding

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsBinding
    private var textIsVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimationsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_animations)

        binding.button.setOnClickListener {
            TransitionManager.beginDelayedTransition(binding.transitionsContainer)

            textIsVisible != textIsVisible
            binding.text.visibility = if (textIsVisible) View.VISIBLE else View.GONE
        }
    }
}