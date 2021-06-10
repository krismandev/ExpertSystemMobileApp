package com.krismanpratama.expertsystem.ui.basispengetahuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.databinding.ActivityAddBasisPengetahuanBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class AddBasisPengetahuanActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBasisPengetahuanBinding
    private lateinit var spinner: Spinner
    private val listPenyakit = ArrayList<Penyakit>()
    private var namaPenyakit = ArrayList<String>()
    private lateinit var addBasisPengetahuanViewModel: AddBasisPengetahuanViewModel
    private var basisPengetahuanMaster: BasisPengetahuanMaster? = null


    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBasisPengetahuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        addBasisPengetahuanViewModel = ViewModelProvider(this, factory)[AddBasisPengetahuanViewModel::class.java]

        spinner = binding.spinnerAddBasisPengetahuan

        addBasisPengetahuanViewModel.getAllPenyakit().observe(this,{
            listPenyakit.add(Penyakit(0,"Pilih Penyakit","-","-"))
            for (item in it){
                listPenyakit.add(item)
                item.namaPenyakit?.let { it1 -> namaPenyakit.add(it1) }
            }
            populatePenyakitSpinner()
        })
    }

    @InternalCoroutinesApi
    fun populatePenyakitSpinner(){
        val spinnerAdapter = SpinnerPenyakitAdapter(this, R.layout.item_spinner2, R.id.spinnerText, listPenyakit)

//        val spinnerAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,namaPenyakit)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            var basisPengetahuanMasterId: Long? =null
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0){
                    val penyakit = spinnerAdapter.getItem(position)

                    val numRow = addBasisPengetahuanViewModel.countBasisPengetahuanByPenyakitId(penyakit.id)
                    Log.d("NUMROW",numRow.toString())
                    if (numRow < 1){
                        basisPengetahuanMaster = BasisPengetahuanMaster(penyakitId = penyakit.id)

                        lifecycleScope.launch {
                            val basisPengetahuanMasterId = addBasisPengetahuanViewModel.addBasisPengetahuanMaster(
                                basisPengetahuanMaster!!
                            )

                            Log.d("BasisIdIf",basisPengetahuanMasterId.toString())
                            setUpRvRules(basisPengetahuanMasterId)
                        }

                    }else{
                        addBasisPengetahuanViewModel.getBasisPengetahuanMasterByPenyakitId(penyakit.id).observe(this@AddBasisPengetahuanActivity,{
                            basisPengetahuanMaster = it
                            Log.d("BasisIdElse",it.id.toString())
                            setUpRvRules(it.id.toLong())
                        })
                    }
//                    if (numRow == 0){
//                        basisPengetahuanMaster = BasisPengetahuanMaster(penyakitId = penyakit.id)
//
//
//                        basisPengetahuanMasterId = addBasisPengetahuanViewModel.addBasisPengetahuanMaster(
//                            basisPengetahuanMaster!!
//                        )
//                        Log.d("BasisId",basisPengetahuanMasterId.toString())
//                        if (basisPengetahuanMasterId != 0L) {
//                            setUpRvRules(basisPengetahuanMasterId!!)
//                        }
//                    }
//                    else if(numRow < 1){
//                        addBasisPengetahuanViewModel.getBasisPengetahuanMasterByPenyakitId(penyakit.id).observe(this@AddBasisPengetahuanActivity,{
//                            basisPengetahuanMaster = it
//                            Log.d("BasisId",it.id.toString())
//                            setUpRvRules(it.id.toLong())
//                        })
//                    }

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

    @InternalCoroutinesApi
    fun setUpRvRules(basisPengetahuanMasterId: Long){
        val rulesAdapter = RulesAdapter(this, basisPengetahuanMasterId.toInt(),this)
//        val arrKeyakinan = arrayOf("Pilih Keyakinan","Sangat Yakin","Yakin","Cukup Yakin","Sedikit Yakin","Tidak Tahu","Tidak")

        addBasisPengetahuanViewModel.getAllGejala().observe(this@AddBasisPengetahuanActivity,{
            rulesAdapter.setData(it)
        })

        with(binding.rvRules){
            layoutManager = LinearLayoutManager(this@AddBasisPengetahuanActivity)
            this.adapter = rulesAdapter
        }
    }

}