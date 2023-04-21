package com.geekbrains.universe.ui.pages.solarsystem.earth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geekbrains.universe.databinding.FragmentEarthBinding
import com.geekbrains.universe.domain.EarthImagingCamera
import com.geekbrains.universe.ui.AppState
import com.geekbrains.universe.ui.utils.QueryDay
import java.time.LocalDate

class EarthFragment : Fragment() {

    companion object {
        fun newInstance() = EarthFragment()
    }

    private lateinit var _binding: FragmentEarthBinding
    private val binding
        get() = _binding!!

    private val viewModel: EarthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getData(QueryDay.aWeekAgo())
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.SuccessPictureOfTheDay -> {
                binding.loadingProcess.visibility = View.GONE
            }

            is AppState.SuccessEarthImagingCamera -> {
                binding.loadingProcess.visibility = View.GONE

                appState.earthImagingCamera?.let {
                    binding.earth.load(it.getImageUrl())
                }
            }

            is AppState.Loading -> {
                binding.loadingProcess.visibility = View.VISIBLE
            }

            is AppState.Error -> {
                binding.loadingProcess.visibility = View.GONE
            }
        }
    }
}