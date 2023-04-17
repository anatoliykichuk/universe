package com.geekbrains.univerce.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.FragmentPictureOfTheDayBinding
import com.geekbrains.universe.ui.AppState
import com.geekbrains.universe.ui.MainActivity
import com.geekbrains.universe.ui.main.PictureOfTheDayViewModel
import com.geekbrains.universe.ui.utils.PicturesDay
import com.google.android.material.bottomappbar.BottomAppBar
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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureOfTheDayBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleView = view.findViewById<TextView>(R.id.bottom_sheet_description_header)
        val descriptionView = view.findViewById<TextView>(R.id.bottom_sheet_description)

        viewModel.getLiveFata().observe(viewLifecycleOwner) {
            renderData(it, titleView, descriptionView)
        }

        viewModel.getPicture(PicturesDay.today())

        binding.searchTextLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                val wikipediaUriTemplate =
                    "https://en.wikipedia.org/wiki/${binding.searchText.text.toString()}"
                data = Uri.parse(wikipediaUriTemplate)
            })
        }

        binding.today.setOnClickListener {
            viewModel.getPicture(PicturesDay.today())
        }

        binding.yesterday.setOnClickListener {
            viewModel.getPicture(PicturesDay.yesterday())
        }

        binding.dayBeforeYesterday.setOnClickListener {
            viewModel.getPicture(PicturesDay.dayBeforeYesterday())
        }

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(appState: AppState, titleView: TextView, descriptionView: TextView) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingProcess.visibility = View.GONE

                appState.pictureOfTheDay?.let {
                    binding.imageView.load(it.getUrlFilled())

                    titleView.text = it.title
                    descriptionView.text = it.explanation
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