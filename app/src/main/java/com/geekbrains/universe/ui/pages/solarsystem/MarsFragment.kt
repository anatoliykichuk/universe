package com.geekbrains.universe.ui.pages.solarsystem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.universe.databinding.FragmentMarsBinding

class MarsFragment : Fragment() {

    companion object {
        fun newInstance() = MarsFragment()
    }

    private lateinit var _binding: FragmentMarsBinding
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMarsBinding.inflate(inflater, container, false)

        return binding.root
    }
}