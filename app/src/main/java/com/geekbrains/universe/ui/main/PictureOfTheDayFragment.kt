package com.geekbrains.univerce.ui.main

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
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
import com.geekbrains.universe.databinding.FragmentPictureOfTheDayBinding
import com.geekbrains.universe.ui.AppState
import com.geekbrains.universe.ui.main.PictureOfTheDayViewModel
import com.geekbrains.universe.ui.utils.QueryDay
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlin.random.Random

class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var isPictureExpanded = false

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

        binding.picture.setOnClickListener {
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
                    binding.picture.load(it.getUrlFilled())

                    setTitleStyle(titleView, it.title)
                    setDescriptionStyle(descriptionView, it.explanation)
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

    private fun setTitleStyle(titleView: TextView, title: String) {
        val spannableString = SpannableString(title)
        titleView.setText(spannableString, TextView.BufferType.SPANNABLE)

        val spannableTitle = titleView.text as Spannable
        val separatorIndex = title.indexOf(":")

        if (separatorIndex < 0) {
            spannableTitle.setSpan(
                ForegroundColorSpan(Color.BLUE),
                0,
                title.length,
                Spannable.SPAN_INCLUSIVE_INCLUSIVE
            )
            return
        }

        spannableTitle.setSpan(
            ForegroundColorSpan(Color.GRAY),
            0,
            separatorIndex,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )

        spannableTitle.setSpan(
            ForegroundColorSpan(Color.DKGRAY),
            separatorIndex,
            title.length,
            Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        )

        titleView.typeface = Typeface.createFromAsset(
            requireContext().assets, "font/stacker/Stacker-jE03l.ttf"
        )
    }

    private fun setDescriptionStyle(descriptionView: TextView, description: String) {
        val spannableString = SpannableString(description)
        descriptionView.setText(spannableString, TextView.BufferType.SPANNABLE)

        val spannableDescription = descriptionView.text as Spannable
        val stepValue = ((description.length / Random.nextInt(description.length)) as Int)

        var isColorRed = false

        for (rangeCount in description.length downTo 1 step stepValue) {
            val foregroundColor = ForegroundColorSpan(if (isColorRed) Color.RED else Color.GREEN)
            var start = rangeCount - stepValue


            if (start < 0) {
                start = 0
            }

            spannableDescription.setSpan(
                foregroundColor, start, rangeCount, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )

            isColorRed = !isColorRed
        }

        descriptionView.typeface = Typeface.createFromAsset(
            requireContext().assets, "font/gepeste/Gepestev-nRJgO.ttf"
        )
    }
}