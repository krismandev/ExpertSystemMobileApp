package com.krismanpratama.expertsystem.ui.konsultasi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import com.krismanpratama.expertsystem.ui.basispengetahuan.AddBasisPengetahuanViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@InternalCoroutinesApi
class BasisPengetahuanSpinnerAdapter(
    private val appCompatActivity: AppCompatActivity,
    context: Context,
    resource: Int,
    spinnerText: Int,
    private val listBasisPengetahuanMaster: List<BasisPengetahuanMaster>)
    : ArrayAdapter<BasisPengetahuanMaster>(context, resource, spinnerText, listBasisPengetahuanMaster) {

    private lateinit var viewModel: KonsultasiViewModel
    init {
        val factory = ViewModelFactory.getInstance(appCompatActivity)
        viewModel = ViewModelProvider(appCompatActivity, factory).get(KonsultasiViewModel::class.java)
    }

    override fun getItem(position: Int): BasisPengetahuanMaster {
        return listBasisPengetahuanMaster[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    private fun initView(position: Int): View {
        val basisPengetahuanMaster: BasisPengetahuanMaster = getItem(position)

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater.inflate(R.layout.item_spinner2, null)
        val textView = v.findViewById<TextView>(R.id.spinnerText)
        if (basisPengetahuanMaster.id == 0){
            textView.text = "Pilih Penyakit"
        }else{
            viewModel.getPenyakitByPenyakitId(basisPengetahuanMaster.penyakitId).observe(appCompatActivity,{
                textView.text = it.namaPenyakit
            })
        }
        return v

    }
}
