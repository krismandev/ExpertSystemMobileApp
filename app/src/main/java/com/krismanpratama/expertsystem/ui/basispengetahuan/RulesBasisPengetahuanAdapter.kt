package com.krismanpratama.expertsystem.ui.basispengetahuan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.krismanpratama.expertsystem.data.entity.RulesBasisPengetahuan
import com.krismanpratama.expertsystem.databinding.ItemRulesBinding
import com.krismanpratama.expertsystem.databinding.ItemRulesPakarBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class RulesBasisPengetahuanAdapter(private val appCompatActivity: AppCompatActivity) : RecyclerView.Adapter<RulesBasisPengetahuanAdapter.RulesBasisPengetahuanViewHolder>() {
    private val lisRules = ArrayList<RulesBasisPengetahuan>()
    private lateinit var viewModel: BasisPengetahuanViewModel

    init {
        val factory = ViewModelFactory.getInstance(appCompatActivity)
        viewModel = ViewModelProvider(appCompatActivity, factory).get(BasisPengetahuanViewModel::class.java)
    }

    inner class RulesBasisPengetahuanViewHolder(private val binding: ItemRulesPakarBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(rulesBasisPengetahuan: RulesBasisPengetahuan){
            viewModel.getGejalaByRule(rulesBasisPengetahuan.gejalaId).observe(appCompatActivity,{
                with(binding){
                    namaGejala.text = it.namaGejala
                    when(rulesBasisPengetahuan.keyakinan){
                        1.0 -> {
                            keyakinan.text = "Sangat Yakin"
                            bobot.text = "(1)"
                        }
                        0.8 -> {
                            keyakinan.text = "Yakin"
                            bobot.text = "(0.8)"
                        }
                        0.6 -> {
                            keyakinan.text = "Cukup Yakin"
                            bobot.text = "(0.6)"
                        }
                        0.4 -> {
                            keyakinan.text = "Sedikit Yakin"
                            bobot.text = "(0.4)"
                        }
                        0.2 -> {
                            keyakinan.text = "Tidak Tahu"
                            bobot.text = "(0.2)"
                        }
                        0.0 -> {
                            keyakinan.text = "Tidak"
                            bobot.text = "(0)"
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
        val itemsRulesBinding = ItemRulesPakarBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return RulesBasisPengetahuanViewHolder(itemsRulesBinding)
    }

    override fun onBindViewHolder(holder: RulesBasisPengetahuanViewHolder, position: Int) {
        val rule = lisRules[position]
        holder.bind(rule)
    }

    override fun getItemCount(): Int = lisRules.size
}