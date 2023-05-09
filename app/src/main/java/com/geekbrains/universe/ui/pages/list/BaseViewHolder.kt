package com.geekbrains.universe.ui.pages.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.geekbrains.universe.domain.ItemData

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(itemData: ItemData)
}