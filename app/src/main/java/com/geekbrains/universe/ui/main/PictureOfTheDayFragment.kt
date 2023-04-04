package com.geekbrains.univerce.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.FragmentPictureOfTheDayBinding
import com.geekbrains.universe.ui.AppState
import com.geekbrains.universe.ui.main.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getLiveFata().observe(viewLifecycleOwner) {
            renderData(it)
        }

        viewModel.getPicture()

        setBottomSheetBehavior(view.findViewById( R.id.bottom_sheet_container))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
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