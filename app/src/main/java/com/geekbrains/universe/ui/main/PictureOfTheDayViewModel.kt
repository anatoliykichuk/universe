package com.geekbrains.universe.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.universe.data.net.Repository
import com.geekbrains.universe.ui.AppState

class PictureOfTheDayViewModel : ViewModel() {

    private val liveData: MutableLiveData<AppState> = MutableLiveData()
    private var dataPosted: Boolean = false

    fun getLiveFata(): LiveData<AppState> {
        return liveData
    }

    fun getPicture() {
        if (dataPosted) {
            return
        }

        Thread {
            liveData.postValue(
                AppState.Success(Repository().getPictureFromNet())
            )
            dataPosted = true
        }.start()
    }
}