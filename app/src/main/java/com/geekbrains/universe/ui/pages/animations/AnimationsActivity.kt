package com.geekbrains.universe.ui.pages.animations

import android.graphics.Rect
import android.os.Bundle
import android.transition.Explode
import android.transition.Transition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.ActivityAnimationsExplodeBinding

private const val ITEM_COUNT = 32

class AnimationsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimationsExplodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnimationsExplodeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_animations_explode)

        binding.recyclerView.adapter = Adapter()
    }

    private fun explode(clickedView: View) {
        val viewRect = Rect()
        clickedView.getGlobalVisibleRect(viewRect)

        val explode = Explode()
        explode.epicenterCallback = object : Transition.EpicenterCallback() {
            override fun onGetEpicenter(transition: Transition?): Rect {
                return viewRect
            }
        }

        explode.duration = 1000
        TransitionManager.beginDelayedTransition(binding.recyclerView, explode)

        binding.recyclerView.adapter = null
    }

    private inner class Adapter : RecyclerView.Adapter<ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.activity_animations_explode_recycler_view_item,
                    parent,
                    false
                ) as View
            )
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener() {
                explode(it)
            }
        }

        override fun getItemCount(): Int {
            return ITEM_COUNT
        }
    }

    private inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}