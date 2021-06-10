package com.krismanpratama.expertsystem.ui.basispengetahuan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.databinding.ActivityRulesBasisPengetahuanBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

class RulesBasisPengetahuanActivity : AppCompatActivity() {
    private lateinit var viewModel: BasisPengetahuanViewModel
    private lateinit var binding: ActivityRulesBasisPengetahuanBinding

    companion object{
        const val EXTRA_BASIS_PENGETAHUAN_ID = "extra_id"
    }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRulesBasisPengetahuanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this,factory)[BasisPengetahuanViewModel::class.java]

        val adapter = RulesBasisPengetahuanAdapter(this)

        val extras = intent.extras
        if (extras != null){
            val basisPengetahuanId = extras.getInt(EXTRA_BASIS_PENGETAHUAN_ID)

            viewModel.getBasisPengetahuanWithRules(basisPengetahuanId).observe(this,{
                adapter.setData(it.rulesBasisPengetahuan)
                populateView(it.basisPengetahuanMaster)
            })

            with(binding){
                rvRules.layoutManager = LinearLayoutManager(this@RulesBasisPengetahuanActivity)
                rvRules.adapter = adapter
            }
        }



    }

    fun populateView(basisPengetahuanMaster: BasisPengetahuanMaster){
        viewModel.getPenyakitByPenyakitId(basisPengetahuanMaster.penyakitId).observe(this,{
            binding.namaPenyakitReceived.text = it.namaPenyakit
        })
    }
}