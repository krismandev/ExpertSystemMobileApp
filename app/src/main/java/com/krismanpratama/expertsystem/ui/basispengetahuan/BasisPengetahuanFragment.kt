package com.krismanpratama.expertsystem.ui.basispengetahuan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.databinding.FragmentBasisPengetahuanBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import com.krismanpratama.expertsystem.ui.penyakit.PenyakitAdapter
import com.krismanpratama.expertsystem.ui.penyakit.PenyakitViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.ArrayList

class BasisPengetahuanFragment : Fragment() {

    private lateinit var spinner: Spinner
    private lateinit var binding: FragmentBasisPengetahuanBinding
    private lateinit var viewModel: BasisPengetahuanViewModel
    @InternalCoroutinesApi
    private lateinit var adapter: BasisPengetahuanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasisPengetahuanBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        spinner = binding.spinnerBasisPengetahuan
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory.getInstance(requireActivity())
        viewModel = ViewModelProvider(this,factory)[BasisPengetahuanViewModel::class.java]

        adapter = BasisPengetahuanAdapter(requireActivity(),this,viewLifecycleOwner,object : BasisPengetahuanAdapter.OnAdapterListener{
            override fun onDelete(basisPengetahuanMaster: BasisPengetahuanMaster) {
                deleteDialog(basisPengetahuanMaster)
            }

        })
        with(binding){
            rvBasisPengetahuanMaster.layoutManager = LinearLayoutManager(context)
            rvBasisPengetahuanMaster.adapter = adapter
            rvBasisPengetahuanMaster.setHasFixedSize(true)
        }

        viewModel.getAllBasisPengetahuanMaster().observe(viewLifecycleOwner,{
            adapter.setData(it as ArrayList<BasisPengetahuanMaster>)
        })



        binding.btnAddBasisPengetahuan.setOnClickListener {
            startActivity(Intent(requireContext(),AddBasisPengetahuanActivity::class.java))
        }

    }

    private fun deleteDialog(basisPengetahuanMaster: BasisPengetahuanMaster){
        val alertDialog = AlertDialog.Builder(requireActivity())
        alertDialog.apply {
            setTitle("Konfirmasi")
            setMessage("Yakin ingin menghapus ?")
            setNegativeButton("Batal") { dialoginterface, i ->
                //menutup alert saat menekan tombol
                dialoginterface.dismiss()
            }
            setPositiveButton("Ya"){dialoginterface,i->
                dialoginterface.dismiss()
                viewModel.deleteBasisPengetahuanMaster(basisPengetahuanMaster)
            }
        }
        alertDialog.show()
    }




}