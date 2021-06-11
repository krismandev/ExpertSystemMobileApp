package com.krismanpratama.expertsystem.ui.konsultasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.databinding.ActivityKonsultasiBinding
import com.krismanpratama.expertsystem.helper.LoadingDialog
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

class KonsultasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKonsultasiBinding
    private lateinit var viewModel: KonsultasiViewModel
    private lateinit var spinner: Spinner
    private val listBasisPengetahuanMaster = ArrayList<BasisPengetahuanMaster>()
    private val listKeyakinanUser = ArrayList<Double>()
    private val listKeyakinanPakar = ArrayList<Double>()
    private val arrCfhe = ArrayList<Double>()

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonsultasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[KonsultasiViewModel::class.java]

        spinner = binding.spinnerBasisPengetahuanKonsultasi

        viewModel.getAllBasisPengetahuanMaster().observe(this,{
            listBasisPengetahuanMaster.add(BasisPengetahuanMaster(0,0))
            for (item in it){
                listBasisPengetahuanMaster.add(item)
            }
            populateSpinnerBasisPengetahuan()
        })

        var hasilCF = 0.0

        binding.btnLihatHasil.setOnClickListener {

//            val loadingDialog = LoadingDialog(this)

            arrCfhe.forEachIndexed lit@{ index, d ->
                if (index == 0){
                    hasilCF = arrCfhe[index] + arrCfhe[index + 1] * (1.0 - arrCfhe[index])

                }else if(index != arrCfhe.size - 1){
                    hasilCF = hasilCF + arrCfhe[index+1] * (1.0 - hasilCF)
                }else{
                    return@lit
                }

            }

//            loadingDialog.loadingAlertDialog()
//            Handler(Looper.getMainLooper()).postDelayed({
//                loadingDialog.dissmissDialog()
//            },5000)


            Toast.makeText(this, hasilCF.toString(), Toast.LENGTH_SHORT).show()
        }



    }

    @InternalCoroutinesApi
    private fun populateSpinnerBasisPengetahuan() {
        val spinnerAdapter = BasisPengetahuanSpinnerAdapter(this,this,R.layout.item_spinner2,R.id.spinnerText,listBasisPengetahuanMaster)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0){
                    val basisPengetahuanMaster = spinnerAdapter.getItem(position)
                    setUpRvRules(basisPengetahuanMaster.id)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }

    @InternalCoroutinesApi
    fun setUpRvRules(basisPengetahuanMasterId: Int){
        val rulesAdapter = RulesKonsultasiAdapter(this,object : RulesKonsultasiAdapter.OnAdapterListener{
            override fun sendKeyakinan(keyakinanUser: Double, keyakinanPakar: Double) {
                listKeyakinanUser.add(keyakinanUser)
                listKeyakinanPakar.add(keyakinanPakar)

                val cfhe = keyakinanUser*keyakinanPakar
                arrCfhe.add(cfhe)

                Log.d("LihatKeyakinanUser",listKeyakinanUser.toString())
                Log.d("LihatKeyakinanPakar",listKeyakinanPakar.toString())
                Log.d("LihatCFHE",arrCfhe.toString())


            }


        })
        viewModel.getBasisPengetahuanWithRules(basisPengetahuanMasterId).observe(this,{
            rulesAdapter.setData(it.rulesBasisPengetahuan)
        })

        with(binding){
            rvRules.layoutManager = LinearLayoutManager(this@KonsultasiActivity)
            rvRules.adapter = rulesAdapter
        }
    }
}