package com.geekbrains.universe.ui.utils

import java.time.LocalDate

object QueryDay {

    private var shiftFromCurrentDate: Int = 0

    fun today(): String {
        return shiftDay()
    }

    fun yesterday(): String {
        shiftFromCurrentDate = 1
        return shiftDay()
    }

    fun dayBeforeYesterday(): String {
        shiftFromCurrentDate = 2
        return shiftDay()
    }

    fun aWeekAgo(): String {
        shiftFromCurrentDate = 7
        return shiftDay()
    }

    private fun shiftDay(): String {
        val today = LocalDate.now()
        val shiftDay = LocalDate.of(
            today.year, today.month, today.dayOfMonth - shiftFromCurrentDate
        )
        return shiftDay.toString()
    }
}