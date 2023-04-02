package com.geekbrains.univerce.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geekbrains.universe.databinding.FragmentPictureOfTheDayBinding
import com.geekbrains.universe.ui.AppState
import com.geekbrains.universe.ui.main.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getLiveFata().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getPicture()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingProcess.visibility = View.GONE

                appState.pictureOfTheDay?.let {
                    binding.imageView.load(it.getUrl())
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