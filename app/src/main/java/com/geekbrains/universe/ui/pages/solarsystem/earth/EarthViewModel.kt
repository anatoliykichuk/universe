package com.geekbrains.universe.ui.pages.solarsystem.earth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geekbrains.universe.data.net.Repository
import com.geekbrains.universe.ui.AppState

class EarthViewModel(
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
                AppState.SuccessEarthImagingCamera(Repository().getEarthImagingCameraFromNet(date))
            )
            dataPosted = true
        }.start()
    }
}