package com.geekbrains.universe.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityMainBinding
import com.geekbrains.universe.ui.pages.animations.AnimationsActivity
import com.geekbrains.universe.ui.pages.list.ListActivity
import com.geekbrains.universe.ui.pages.solarsystem.PagerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setTheme(R.style.Theme_Moon)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
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
        startActivity(Intent(this@MainActivity, ListActivity::class.java))
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