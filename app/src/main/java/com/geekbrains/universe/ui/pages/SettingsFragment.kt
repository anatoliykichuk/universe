package com.geekbrains.universe.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.universe.R
import com.geekbrains.universe.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moonTheme.setOnClickListener {
            activity?.setTheme(R.style.Theme_Moon)
        }

        binding.martianTheme.setOnClickListener {
            activity?.setTheme(R.style.Theme_Martian)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}