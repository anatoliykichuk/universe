package com.geekbrains.universe.ui.pages.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.universe.R
import com.geekbrains.universe.domain.ItemData

class ListAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private val data: MutableList<ItemData>
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            ItemData.TYPE_EARTH -> EarthViewHolder(
                inflater.inflate(R.layout.activity_list_item_earth, parent, false)
            ) as BaseViewHolder
            ItemData.TYPE_MARS -> MarsViewHolder(
                inflater.inflate(R.layout.activity_list_item_mars, parent, false)
            ) as BaseViewHolder
            else -> HeaderViewHolder(
                inflater.inflate(R.layout.activity_list_item_header, parent, false)
            ) as BaseViewHolder
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].type
    }

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(itemData: ItemData) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.description).text = itemData.description
                itemView.findViewById<ImageView>(R.id.wiki_image).setOnClickListener {
                    onListItemClickListener.onItemClick(itemData)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(itemData: ItemData) {
            itemView.findViewById<ImageView>(R.id.mars_image).setOnClickListener {
                onListItemClickListener.onItemClick(itemData)
            }

            itemView.findViewById<ImageView>(R.id.add_item).setOnClickListener {
                addItem(layoutPosition)
            }

            itemView.findViewById<ImageView>(R.id.remove_item).setOnClickListener {
                removeItem(layoutPosition)
            }
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(itemData: ItemData) {
            itemView.setOnClickListener {
                onListItemClickListener.onItemClick(itemData)
            }
        }
    }

    fun appendItem() {
        data.add(getItem())
        notifyItemInserted(itemCount - 1)
    }

    private fun addItem(layoutPosition: Int) {
        data.add(layoutPosition, getItem())
        notifyItemInserted(layoutPosition)
    }

    private fun removeItem(layoutPosition: Int) {
        data.removeAt(layoutPosition)
        notifyItemRemoved(layoutPosition)
    }

    private fun getItem() = ItemData(ItemData.TYPE_MARS, "Mars", "")
}