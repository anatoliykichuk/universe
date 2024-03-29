package com.geekbrains.universe.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.universe.data.net.Repository
import com.geekbrains.universe.ui.AppState

class PictureOfTheDayViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData()
) : ViewModel() {

    private var dataPosted: Boolean = false

    fun getLiveData(): LiveData<AppState> {
        return liveData
    }

    fun getData(date: String) {
        liveData.value = AppState.Loading

        if (dataPosted) {
            return
        }

        Thread {
            liveData.postValue(
                AppState.SuccessPictureOfTheDay(Repository().getPictureOfTheDayFromNet(date))
            )
            dataPosted = true
        }.start()
    }
}