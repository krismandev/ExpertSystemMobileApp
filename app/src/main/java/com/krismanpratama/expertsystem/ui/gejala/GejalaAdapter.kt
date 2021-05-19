package com.krismanpratama.expertsystem.ui.gejala

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.with
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.databinding.ItemGejalaBinding
import com.krismanpratama.expertsystem.ui.gejala.GejalaAdapter
import java.util.*

class GejalaAdapter(private val listener: OnAdapterListener): RecyclerView.Adapter<GejalaAdapter.ListGejalaViewHolder>() {
    private val listGejala: ArrayList<Gejala> = ArrayList()

    inner class ListGejalaViewHolder(private val binding: ItemGejalaBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(gejala: Gejala){
            with(binding){
                namaGejalaReceived.text = gejala.namaGejala

                namaGejalaReceived.setOnClickListener {
                    listener.onClick(gejala)
                }
                iconEdit.setOnClickListener {
                    listener.onEdit(gejala)
                }
                iconDelete.setOnClickListener {
                    listener.onDelete(gejala)
                }

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListGejalaViewHolder {
        val itemsGejalaBinding = ItemGejalaBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return ListGejalaViewHolder(itemsGejalaBinding)
    }

    override fun onBindViewHolder(holder: ListGejalaViewHolder, position: Int) {
        holder.bind(listGejala[position])
    }

    override fun getItemCount(): Int = listGejala.size

    fun setData(gejala: ArrayList<Gejala>) {
        listGejala.clear()
        listGejala.addAll(gejala)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick(gejala: Gejala)
        fun onEdit(gejala: Gejala)
        fun onDelete(gejala: Gejala)
    }

}