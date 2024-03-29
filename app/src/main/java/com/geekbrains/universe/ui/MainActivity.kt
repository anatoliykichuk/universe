package com.geekbrains.universe.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.AnticipateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityMainBinding
import com.geekbrains.universe.ui.pages.animations.AnimationsActivity
import com.geekbrains.universe.ui.pages.notelist.NoteListActivity
import com.geekbrains.universe.ui.pages.solarsystem.PagerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_Universe)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        var isHideSplashScreen = false

        object : CountDownTimer(2000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            override fun onFinish() {
                isHideSplashScreen = true
            }
        }.start()

        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnDrawListener {
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (isHideSplashScreen) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        }

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val slideLeft = ObjectAnimator.ofFloat(
                splashScreenView,
                View.TRANSLATION_X,
                0f,
                -splashScreenView.height.toFloat()
            )
            slideLeft.interpolator = AnticipateInterpolator()
            slideLeft.duration = 1000L

            slideLeft.doOnEnd { splashScreenView.remove() }
            slideLeft.start()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
                goToListPage()
                true
            }
            R.id.action_animations -> {
                goToAnimationsPage()
                true
            }
            R.id.action_solar_system -> {
                goToSolarSystemPages()
                true
            }
            R.id.action_settings -> {
                goToSettingsPage()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun goToListPage() {
        startActivity(Intent(this@MainActivity, NoteListActivity::class.java))
    }

    private fun goToAnimationsPage() {
        startActivity(Intent(this@MainActivity, AnimationsActivity::class.java))
    }

    private fun goToSolarSystemPages() {
        startActivity(Intent(this@MainActivity, PagerActivity::class.java))
    }

    private fun goToSettingsPage() {
        findNavController(R.id.nav_host_fragment_content_main)
            .navigate(R.id.action_MainFragment_to_SettingsFragment)
    }
}