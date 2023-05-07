package com.geekbrains.universe.ui.pages.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.geekbrains.universe.R
import com.geekbrains.universe.domain.ItemData

class ListActivityAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private val data: List<ItemData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == ItemData.TYPE_EARTH) {
            EarthViewHolder(
                inflater.inflate(R.layout.activity_list_item_earth, parent, false)
            ) as ViewHolder
        } else {
            MarsViewHolder(
                inflater.inflate(R.layout.activity_list_item_mars, parent, false)
            ) as ViewHolder
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == ItemData.TYPE_EARTH) {
            (holder as EarthViewHolder).bind(data[position])
        } else {
            (holder as MarsViewHolder).bind(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    inner class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(itemData: ItemData) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.description).text = itemData.description
                itemView.findViewById<ImageView>(R.id.wiki_image).setOnClickListener {
                    onListItemClickListener.onItemClick(itemData)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(itemData: ItemData) {
            itemView.findViewById<ImageView>(R.id.mars_image).setOnClickListener {
                onListItemClickListener.onItemClick(itemData)
            }
        }
    }
}