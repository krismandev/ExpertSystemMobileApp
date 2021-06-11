package com.krismanpratama.expertsystem.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.databinding.FragmentHomeBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import com.krismanpratama.expertsystem.ui.konsultasi.KonsultasiActivity
import kotlinx.coroutines.InternalCoroutinesApi

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this,factory)[HomeViewModel::class.java]
        setUpListener()
    }

    private fun setUpListener() {
        binding.btnMulaiKonsultasi.setOnClickListener {
            val intent = Intent(requireContext(),KonsultasiActivity::class.java)
            startActivity(intent)
        }
    }
}