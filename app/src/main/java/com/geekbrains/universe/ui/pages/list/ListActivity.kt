package com.geekbrains.universe.ui.pages.list

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.universe.databinding.ActivityListBinding
import com.geekbrains.universe.domain.ItemData

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

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
            getSampleData()
        )

        binding.list.adapter = adapter
        binding.listActionButton.setOnClickListener { adapter.appendItem() }
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