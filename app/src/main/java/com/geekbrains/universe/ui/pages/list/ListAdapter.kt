package com.geekbrains.universe.ui.pages.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.universe.R
import com.geekbrains.universe.domain.ItemData

class ListAdapter(
    private val onListItemClickListener: OnListItemClickListener,
    private var data: MutableList<Pair<ItemData, Boolean>>,
    private val dragListener: OnStartDragListener
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    inner class EarthViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(itemData: Pair<ItemData, Boolean>) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.description).text = itemData.first.description
                itemView.findViewById<ImageView>(R.id.wiki_image).setOnClickListener {
                    onListItemClickListener.onItemClick(itemData.first)
                }
            }
        }
    }

    inner class MarsViewHolder(view: View) : BaseViewHolder(view), ItemTouchViewHolder {
        override fun bind(itemData: Pair<ItemData, Boolean>) {
            itemView.findViewById<ImageView>(R.id.mars_image).setOnClickListener {
                onListItemClickListener.onItemClick(itemData.first)
            }

            itemView.findViewById<TextView>(R.id.mars_description).visibility =
                if (itemData.second) View.VISIBLE else View.GONE

            itemView.findViewById<TextView>(R.id.mars_title).setOnClickListener {
                toggleText(layoutPosition)
            }

            itemView.findViewById<ImageView>(R.id.add_item).setOnClickListener {
                addItem(layoutPosition)
            }

            itemView.findViewById<ImageView>(R.id.remove_item).setOnClickListener {
                removeItem(layoutPosition)
            }

            itemView.findViewById<ImageView>(R.id.move_up_item).setOnClickListener {
                moveUpItem(layoutPosition)
            }

            itemView.findViewById<ImageView>(R.id.move_down_item).setOnClickListener {
                moveDownItem(layoutPosition)
            }

            itemView.findViewById<ImageView>(R.id.move_item).setOnTouchListener { _, event ->
                if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(this)
                }
                false
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    inner class HeaderViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(itemData: Pair<ItemData, Boolean>) {
            itemView.setOnClickListener {
                onListItemClickListener.onItemClick(itemData.first)
            }
        }
    }

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
        return data[position].first.type
    }


    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition).apply {
            data.add(
                if (toPosition > fromPosition) toPosition - 1 else toPosition, this
            )
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
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

    private fun moveUpItem(layoutPosition: Int) {
        layoutPosition.takeIf { it > 1 }?.also { currentPosition ->
            data.removeAt(currentPosition).apply {
                data.add(currentPosition - 1, this)
            }
            notifyItemMoved(currentPosition, currentPosition - 1)
        }
    }

    private fun moveDownItem(layoutPosition: Int) {
        layoutPosition.takeIf { it < data.size - 1 }?.also { currentPosition ->
            data.removeAt(currentPosition).apply {
                data.add(currentPosition + 1, this)
            }
            notifyItemMoved(currentPosition, currentPosition + 1)
        }
    }

    private fun getItem() = Pair(ItemData(ItemData.TYPE_MARS, "Mars", ""), false)

    private fun toggleText(layoutPosition: Int) {
        data[layoutPosition] = data[layoutPosition].let {
            it.first to !it.second
        }
        notifyItemChanged(layoutPosition)
    }
}