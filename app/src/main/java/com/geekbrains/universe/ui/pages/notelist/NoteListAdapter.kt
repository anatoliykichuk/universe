package com.geekbrains.universe.ui.pages.notelist

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.universe.R
import com.geekbrains.universe.domain.ItemData

class NoteListAdapter(
    private var data: MutableList<ItemData>,
    private val dragListener: OnStartDragListener
) : RecyclerView.Adapter<NoteListAdapter.NoteListViewHolder>(), ItemTouchHelperAdapter {

    inner class NoteListViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view), ItemTouchViewHolder {

         @SuppressLint("ClickableViewAccessibility")
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
    ): NoteListAdapter.NoteListViewHolder {

        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_note_list_item, parent, false)

        return NoteListViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteListViewHolder, position: Int) {
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

    private fun getItem() = ItemData()
}