package com.geekbrains.universe.ui.pages.list

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.geekbrains.universe.databinding.ActivityListBinding
import com.geekbrains.universe.domain.ItemData

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.list.adapter = ListAdapter(
            object : OnListItemClickListener {
                override fun onItemClick(itemData: ItemData) {
                    Toast
                        .makeText(this@ListActivity, itemData.text, Toast.LENGTH_SHORT)
                        .show()
                }
            },
            getSampleData()
        )
    }

    private fun getSampleData(): List<ItemData> {
        return arrayListOf(
            ItemData(ItemData.TYPE_EARTH, "Header"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_MARS, "Mars", ""),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_EARTH, "Earth"),
            ItemData(ItemData.TYPE_MARS, "Mars", "")
        )
    }
}