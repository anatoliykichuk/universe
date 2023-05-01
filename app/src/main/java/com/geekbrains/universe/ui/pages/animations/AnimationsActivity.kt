package com.geekbrains.universe.ui.pages.animations

import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityAnimationsEnlargeBinding

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsEnlargeBinding
    private var isExpanded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimationsEnlargeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_animations_enlarge)

        binding.imageView.setOnClickListener {
            isExpanded != isExpanded

            TransitionManager.beginDelayedTransition(
                binding.container,
                TransitionSet().addTransition(ChangeBounds()).addTransition(ChangeImageTransform())
            )

            binding.imageView.layoutParams.height =
                if (isExpanded)
                    ViewGroup.LayoutParams.MATCH_PARENT
                else
                    ViewGroup.LayoutParams.WRAP_CONTENT

            binding.imageView.scaleType =
                if (isExpanded)
                    ImageView.ScaleType.CENTER_CROP
                else
                    ImageView.ScaleType.FIT_CENTER
        }
    }
}