package com.geekbrains.universe.ui.pages.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.universe.databinding.ActivityListBinding
import com.geekbrains.universe.domain.ItemData

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ListAdapter(
            object : OnListItemClickListener {
                override fun onItemClick(itemData: ItemData) {
                    Toast
                        .makeText(this@ListActivity, itemData.text, Toast.LENGTH_SHORT)
                        .show()
                }
            },
            getSampleData(),
            object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    itemTouchHelper.startDrag(viewHolder)
                }
            }
        )

        binding.list.adapter = adapter
        binding.listActionButton.setOnClickListener { adapter.appendItem() }

        itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback(adapter))
        itemTouchHelper.attachToRecyclerView(binding.list)
    }

    private fun getSampleData(simple: Boolean = true): MutableList<Pair<ItemData, Boolean>> {
        return if (simple) {
            arrayListOf(
                Pair(ItemData(ItemData.TYPE_MARS, "Mars", ""), false)
            )
        } else {
            arrayListOf(
                Pair(ItemData(ItemData.TYPE_EARTH, "Header"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_MARS, "Mars", ""), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_EARTH, "Earth"), false),
                Pair(ItemData(ItemData.TYPE_MARS, "Mars", ""), false)
            )
        }
    }
}