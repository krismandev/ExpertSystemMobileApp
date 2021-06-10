package com.krismanpratama.expertsystem.ui.basispengetahuan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.krismanpratama.expertsystem.R
import com.krismanpratama.expertsystem.data.entity.Penyakit

class SpinnerPenyakitAdapter(
    context: Context,
    resource: Int,
    spinnerText: Int,
    private val listPenyakit: List<Penyakit>)
    : ArrayAdapter<Penyakit>(context, resource, spinnerText, listPenyakit) {

    override fun getItem(position: Int): Penyakit {
        return listPenyakit[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position)
    }

    private fun initView(position: Int): View {
        val penyakit: Penyakit = getItem(position)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = inflater.inflate(R.layout.item_spinner2, null)
        val textView = v.findViewById<TextView>(R.id.spinnerText)
        textView.setText(penyakit.namaPenyakit)
        return v
    }
}
