package com.geekbrains.universe.ui.pages.splash

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.universe.databinding.ActivitySplashBinding
import com.geekbrains.universe.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    private val rotateValue = 750f
    private val interpolatorDuration = 10000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splashImage.animate().rotationBy(rotateValue)
            .setInterpolator(LinearInterpolator()).setDuration(interpolatorDuration)
            .setListener(object : Animator.AnimatorListener {
                override fun onAnimationEnd(animation: Animator) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAnimationStart(animation: Animator) {}

                override fun onAnimationCancel(animation: Animator) {}

                override fun onAnimationRepeat(animation: Animator) {}

            })
    }
}