package com.krismanpratama.expertsystem.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
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

    private var menu: Menu? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.generate_menu,menu)
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_generate_data){
            viewModel.generateListGejala()
            viewModel.generateListPenyakit()
            return true
        }
        return super.onOptionsItemSelected(item)



    }
}