package com.krismanpratama.expertsystem.ui.basispengetahuan

import android.R
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.RulesBasisPengetahuan
import com.krismanpratama.expertsystem.databinding.ItemRulesBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class RulesAdapter(private val context: Context, private val basisPengetahuanMasterId: Int, private val appCompatActivity: AppCompatActivity) : RecyclerView.Adapter<RulesAdapter.RulesViewHolder>() {
    private lateinit var viewModel: AddBasisPengetahuanViewModel
    private val listGejala=ArrayList<Gejala>()
    private val arrKeyakinan = arrayOf("Pilih","Sangat Yakin","Yakin","Cukup Yakin","Sedikit Yakin","Tidak Tahu","Tidak")

    init {
        val factory = ViewModelFactory.getInstance(appCompatActivity)
        viewModel = ViewModelProvider(appCompatActivity, factory).get(AddBasisPengetahuanViewModel::class.java)
    }

    inner class RulesViewHolder(private val binding: ItemRulesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(gejala: Gejala){
            var doubKeyakinan: Double? = null
            lateinit var rulesBasisPengetahuan: RulesBasisPengetahuan
            with(binding){
                namaGejala.text = gejala.namaGejala

                val spinnerKeyakinanAdapter = ArrayAdapter(context, R.layout.simple_list_item_1,arrKeyakinan)
                spinnerKeyakinan.adapter = spinnerKeyakinanAdapter
                spinnerKeyakinan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        Log.d("DIPILIH","OKE")
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
                            Log.d("BasisMaster",basisPengetahuanMasterId.toString())
                            Log.d("GejalaId",gejala.id.toString())
                            val numRowRules = viewModel.countRulesOnBasisPengetahuanByGejalaId(basisPengetahuanMasterId,gejala.id)
                            Log.d("Jumlah",numRowRules.toString())
                            if (numRowRules == 0){
                                val newRule = RulesBasisPengetahuan(basisPengetahuanMasterId = basisPengetahuanMasterId, gejalaId = gejala.id, keyakinan = doubKeyakinan!!)
                                viewModel.addRulesBasisPengetahuan(newRule)
                                Log.d("ADDED",newRule.toString())
                            }else if(numRowRules == 1){

                                viewModel.getRulesByGejalaId(basisPengetahuanMasterId,gejala.id).observe(appCompatActivity,{
                                    rulesBasisPengetahuan = it
                                    viewModel.updateRulesByGejalaId(rulesBasisPengetahuan)
                                })
                            }

                        }


                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                }
            }

        }
    }


    fun setData(gejala: List<Gejala>?) {
        if (gejala == null) return
        listGejala.clear()
        listGejala.addAll(gejala)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RulesViewHolder {
        val itemsRulesBinding = ItemRulesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return RulesViewHolder(itemsRulesBinding)
    }

    override fun onBindViewHolder(holder: RulesViewHolder, position: Int) {
        val gejala = listGejala[position]
        holder.bind(gejala)
    }

    override fun getItemCount(): Int = listGejala.size

    interface OnAdapterListener{
        fun onItemSpinnerSelected()
    }

}