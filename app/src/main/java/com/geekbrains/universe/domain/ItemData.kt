package com.geekbrains.universe.domain

data class ItemData(
    val type: Int = TYPE_EARTH,
    val text: String = "Text",
    val description: String = "Description"
) {
    companion object {
        const val TYPE_EARTH = 0
        const val TYPE_MARS = 1
        const val TYPE_HEADER = 2
    }
}
