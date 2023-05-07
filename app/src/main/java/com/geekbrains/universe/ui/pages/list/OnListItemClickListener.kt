package com.geekbrains.universe.ui.pages.list

import com.geekbrains.universe.domain.ItemData

interface OnListItemClickListener {
    fun onItemClick(itemData: ItemData)
}