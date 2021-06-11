package com.krismanpratama.expertsystem.ui.konsultasi

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.data.entity.RulesBasisPengetahuan
import com.krismanpratama.expertsystem.databinding.ItemRulesBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import com.krismanpratama.expertsystem.ui.basispengetahuan.AddBasisPengetahuanViewModel
import com.krismanpratama.expertsystem.ui.basispengetahuan.BasisPengetahuanViewModel
import com.krismanpratama.expertsystem.ui.basispengetahuan.RulesBasisPengetahuanAdapter
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class RulesKonsultasiAdapter(private val appCompatActivity: AppCompatActivity, private val listener: OnAdapterListener) : RecyclerView.Adapter<RulesKonsultasiAdapter.RulesBasisPengetahuanViewHolder>() {
    private val lisRules = ArrayList<RulesBasisPengetahuan>()
    private lateinit var viewModel: BasisPengetahuanViewModel
    private val arrKeyakinan = arrayOf("Pilih","Sangat Yakin","Yakin","Cukup Yakin","Sedikit Yakin","Tidak Tahu","Tidak")

    init {
        val factory = ViewModelFactory.getInstance(appCompatActivity)
        viewModel = ViewModelProvider(appCompatActivity, factory).get(BasisPengetahuanViewModel::class.java)
    }

    inner class RulesBasisPengetahuanViewHolder(private val binding: ItemRulesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(rulesBasisPengetahuan: RulesBasisPengetahuan){
            var doubKeyakinan: Double = 0.0
            viewModel.getGejalaByRule(rulesBasisPengetahuan.gejalaId).observe(appCompatActivity,{
                with(binding){
                    namaGejala.text = it.namaGejala
                    val spinnerKeyakinanAdapter = ArrayAdapter(appCompatActivity, R.layout.simple_list_item_1,arrKeyakinan)
                    spinnerKeyakinan.adapter = spinnerKeyakinanAdapter
                    spinnerKeyakinan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(
                            parent: AdapterView<*>?,
                            view: View?,
                            position: Int,
                            id: Long
                        ) {
                            if (position != 0){
                                when(position){
                                    1 -> {
                                        doubKeyakinan = 1.0
                                    }
                                    2 -> {
                                        doubKeyakinan = 0.8
                                    }
                                    3 -> {
                                        doubKeyakinan = 0.6
                                    }
                                    4 ->{
                                        doubKeyakinan = 0.4
                                    }
                                    5 -> {
                                        doubKeyakinan = 0.2
                                    }
                                    6 -> {
                                        doubKeyakinan = 0.0
                                    }
                                }
                                listener.sendKeyakinan(doubKeyakinan,rulesBasisPengetahuan.keyakinan)
                            }
                        }

                        override fun onNothingSelected(parent: AdapterView<*>?) {

                        }

                    }
                }
            })
        }
    }

    fun setData(rulesBasisPengetahuan: List<RulesBasisPengetahuan>) {
        if (rulesBasisPengetahuan == null) return
        lisRules.clear()
        lisRules.addAll(rulesBasisPengetahuan)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RulesBasisPengetahuanViewHolder {
        val itemsRulesBinding = ItemRulesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return RulesBasisPengetahuanViewHolder(itemsRulesBinding)
    }

    override fun onBindViewHolder(holder: RulesBasisPengetahuanViewHolder, position: Int) {
        val rule = lisRules[position]
        holder.bind(rule)
    }

    override fun getItemCount(): Int = lisRules.size

    interface OnAdapterListener{
        fun sendKeyakinan(keyakinanUser: Double, keyakinanPakar: Double)
    }
}