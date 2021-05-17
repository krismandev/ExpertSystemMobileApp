package com.krismanpratama.expertsystem.ui.penyakit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.databinding.FragmentPenyakitBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory


class PenyakitFragment : Fragment() {

    private lateinit var binding: FragmentPenyakitBinding
    private lateinit var penyakitViewModel: PenyakitViewModel
    private lateinit var adapter: PenyakitAdapter
    private var listPenyakit: ArrayList<Penyakit> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPenyakitBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        val factory = ViewModelFactory.getInstance(requireActivity())
        penyakitViewModel = ViewModelProvider(this, factory)[PenyakitViewModel::class.java]
        setupRecyclerView()
        penyakitViewModel.getAllPenyakit().observe(viewLifecycleOwner,{
            adapter.setData(it as ArrayList<Penyakit>)
        })



    }

    fun setupListener(){
        binding.btnAddPenyakit.setOnClickListener {
            startActivityForResult(Intent(context,AddPenyakitActivity::class.java),AddPenyakitActivity.REQUEST_ADD)
        }
    }

    fun setupRecyclerView(){
        adapter = PenyakitAdapter()
        adapter.notifyDataSetChanged()

        binding.rvPenyakit.layoutManager = LinearLayoutManager(context)
        binding.rvPenyakit.adapter = adapter
        binding.rvPenyakit.setHasFixedSize(true)
    }



}