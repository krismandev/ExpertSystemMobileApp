package com.krismanpratama.expertsystem.ui.penyakit

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.databinding.ItemPenyakitBinding

class PenyakitAdapter(): RecyclerView.Adapter<PenyakitAdapter.ListPenyakitViewHolder>() {
    private val listPenyakit: ArrayList<Penyakit> = ArrayList()

    inner class ListPenyakitViewHolder(private val binding: ItemPenyakitBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(penyakit: Penyakit){
            with(binding){
                namaPenyakitReceived.text = penyakit.namaPenyakit
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListPenyakitViewHolder {
        val itemsPenyakitBinding = ItemPenyakitBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ListPenyakitViewHolder(itemsPenyakitBinding)
    }

    override fun onBindViewHolder(holder: ListPenyakitViewHolder, position: Int) {
        holder.bind(listPenyakit[position])
    }

    override fun getItemCount(): Int = listPenyakit.size

    fun setData(penyakit: ArrayList<Penyakit>) {
        listPenyakit.clear()
        listPenyakit.addAll(penyakit)
        notifyDataSetChanged()
    }

}