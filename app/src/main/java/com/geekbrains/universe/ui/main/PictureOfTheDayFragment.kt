package com.geekbrains.univerce.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.FragmentPictureOfTheDayBinding
import com.geekbrains.universe.ui.AppState
import com.geekbrains.universe.ui.main.PictureOfTheDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import java.time.LocalDate


class PictureOfTheDayFragment : Fragment() {

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }

    private var _binding: FragmentPictureOfTheDayBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var selectedDate: String = today()

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

        viewModel.getPicture(selectedDate)

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                val wikipediaUriTemplate =
                    "https://en.wikipedia.org/wiki/${binding.inputEditText.text.toString()}"
                data = Uri.parse(wikipediaUriTemplate)
            })
        }

        binding.today.setOnClickListener {
            selectedDate = today()
            viewModel.getPicture(selectedDate)
        }

        binding.yesterday.setOnClickListener {
            selectedDate = yesterday()
            viewModel.getPicture(selectedDate)
        }

        binding.dayBeforeYesterday.setOnClickListener {
            selectedDate = dayBeforeYesterday()
            viewModel.getPicture(selectedDate)
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

    private fun today(): String {
        return LocalDate.now().toString()
    }

    private fun yesterday(): String {
        val today = LocalDate.now()
        val yesterday = LocalDate.of(
            today.year, today.month, today.dayOfMonth - 1
        )
        return yesterday.toString()
    }

    private fun dayBeforeYesterday(): String {
        val today = LocalDate.now()
        val dayBeforeYesterday = LocalDate.of(
            today.year, today.month, today.dayOfMonth - 2
        )
        return dayBeforeYesterday.toString()
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