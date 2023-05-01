package com.geekbrains.univerce.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.transition.ChangeBounds
import android.transition.ChangeImageTransform
import android.transition.TransitionManager
import android.transition.TransitionSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.FragmentPictureOfTheDayStartBinding
import com.geekbrains.universe.ui.AppState
import com.geekbrains.universe.ui.main.PictureOfTheDayViewModel
import com.geekbrains.universe.ui.utils.QueryDay
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private var _binding: FragmentPictureOfTheDayStartBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var isPictureExpanded = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPictureOfTheDayStartBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val titleView = view.findViewById<TextView>(R.id.bottom_sheet_description_header)
        val descriptionView = view.findViewById<TextView>(R.id.bottom_sheet_description)

        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it, titleView, descriptionView)
        }

        viewModel.getData(QueryDay.today())

        binding.searchTextLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                val wikipediaUriTemplate =
                    "https://en.wikipedia.org/wiki/${binding.searchText.text.toString()}"
                data = Uri.parse(wikipediaUriTemplate)
            })
        }

        binding.today.setOnClickListener {
            viewModel.getData(QueryDay.today())
        }

        binding.yesterday.setOnClickListener {
            viewModel.getData(QueryDay.yesterday())
        }

        binding.dayBeforeYesterday.setOnClickListener {
            viewModel.getData(QueryDay.dayBeforeYesterday())
        }

        binding.imageView.setOnClickListener {
            isPictureExpanded != isPictureExpanded

            TransitionManager.beginDelayedTransition(
                binding.container,
                TransitionSet().addTransition(ChangeBounds()).addTransition(ChangeImageTransform())
            )

            changeImageParams(it as ImageView)
        }

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }

    private fun changeImageParams(picture: ImageView) {
        picture.layoutParams.height =
            if (isPictureExpanded)
                ViewGroup.LayoutParams.MATCH_PARENT
            else
                ViewGroup.LayoutParams.WRAP_CONTENT

        picture.scaleType =
            if (isPictureExpanded)
                ImageView.ScaleType.CENTER_CROP
            else
                ImageView.ScaleType.FIT_CENTER

    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun renderData(appState: AppState, titleView: TextView, descriptionView: TextView) {
        when (appState) {
            is AppState.SuccessPictureOfTheDay -> {
                binding.loadingProcess.visibility = View.GONE

                appState.pictureOfTheDay?.let {
                    binding.imageView.load(it.getUrlFilled())

                    titleView.text = it.title
                    descriptionView.text = it.explanation
                }
            }

            is AppState.SuccessEarthImagingCamera -> {
                binding.loadingProcess.visibility = View.VISIBLE
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