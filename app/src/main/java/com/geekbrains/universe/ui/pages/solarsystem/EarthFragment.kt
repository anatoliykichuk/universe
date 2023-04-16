package com.geekbrains.universe.ui.pages.solarsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.universe.databinding.FragmentEarthBinding

class EarthFragment : Fragment() {

    companion object {
        fun newInstance() = EarthFragment()
    }

    private lateinit var _binding: FragmentEarthBinding
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)

        return binding.root
    }
}