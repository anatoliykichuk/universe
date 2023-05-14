package com.geekbrains.universe.ui.pages.todolist

import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.universe.R
import com.geekbrains.universe.domain.ItemData

class ToDoListAdapter(
    private var data: MutableList<ItemData>,
    private val dragListener: OnStartDragListener
) : RecyclerView.Adapter<ToDoListAdapter.ToDoListViewHolder>(), ItemTouchHelperAdapter {

    inner class ToDoListViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view), ItemTouchViewHolder {

        fun bind(itemData: ItemData) {
            itemView.findViewById<ImageView>(R.id.item_move).setOnTouchListener { _, event ->
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

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ToDoListAdapter.ToDoListViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_to_do_list_item, parent, false)

        return ToDoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
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

    private fun getItem() = ItemData()
}