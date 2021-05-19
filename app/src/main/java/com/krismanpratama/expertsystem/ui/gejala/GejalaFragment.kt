package com.krismanpratama.expertsystem.ui.gejala

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.databinding.FragmentGejalaBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import com.krismanpratama.expertsystem.ui.gejala.AddGejalaActivity
import com.krismanpratama.expertsystem.ui.gejala.GejalaAdapter
import com.krismanpratama.expertsystem.ui.gejala.GejalaViewModel
import java.util.*


class GejalaFragment : Fragment() {
    private lateinit var binding: FragmentGejalaBinding
    private lateinit var gejalaViewModel: GejalaViewModel
    private lateinit var adapter: GejalaAdapter
    private var listGejala: ArrayList<Gejala> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGejalaBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListener()
        val factory = ViewModelFactory.getInstance(requireActivity())
        gejalaViewModel = ViewModelProvider(this, factory)[GejalaViewModel::class.java]
        setupRecyclerView()
        gejalaViewModel.getAllGejala().observe(viewLifecycleOwner,{
            adapter.setData(it as ArrayList<Gejala>)
        })

    }

    fun setupListener(){
        binding.btnAddGejala.setOnClickListener {
            startActivityForResult(
                Intent(context, AddGejalaActivity::class.java).putExtra(
                    AddGejalaActivity.REQUEST_TYPE,
                    AddGejalaActivity.REQUEST_ADD), AddGejalaActivity.REQUEST_ADD)
        }

    }

    fun setupRecyclerView(){
        adapter = GejalaAdapter(object : GejalaAdapter.OnAdapterListener{
            override fun onClick(gejala: Gejala) {
                intentAddActivity(gejala, AddGejalaActivity.REQUEST_READ)
            }

            override fun onEdit(gejala: Gejala) {
                intentAddActivity(gejala, AddGejalaActivity.REQUEST_UPDATE)
            }

            override fun onDelete(gejala: Gejala) {
                deleteDialog(gejala)
            }

        })
        adapter.notifyDataSetChanged()

        binding.rvGejala.layoutManager = LinearLayoutManager(context)
        binding.rvGejala.adapter = adapter
        binding.rvGejala.setHasFixedSize(true)
    }

    fun intentAddActivity(gejala: Gejala, requestType: Int){
        startActivity(
            Intent(context, AddGejalaActivity::class.java)
            .putExtra(AddGejalaActivity.REQUEST_TYPE,requestType)
            .putExtra(AddGejalaActivity.EXTRA_GEJALA,gejala)
        )
    }

    private fun deleteDialog(gejala: Gejala){
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus ${gejala.namaGejala}")
            setNegativeButton("Batal") { dialoginterface, i ->
                //menutup alert saat menekan tombol
                dialoginterface.dismiss()
            }
            setPositiveButton("Ya"){dialoginterface,i->
                dialoginterface.dismiss()
                gejalaViewModel.deleteGejala(gejala)
            }
        }
        alertDialog.show()
    }



}