package com.krismanpratama.expertsystem.ui.basispengetahuan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.databinding.ItemBasisPengetahuanBinding
import com.krismanpratama.expertsystem.databinding.ItemGejalaBinding
import com.krismanpratama.expertsystem.helper.ViewModelFactory
import com.krismanpratama.expertsystem.ui.gejala.AddGejalaViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.ArrayList

@InternalCoroutinesApi
class BasisPengetahuanAdapter(private val fragmentActivity: FragmentActivity, private val fragment: Fragment, private val viewLifecycleOwner: LifecycleOwner, private val listener: OnAdapterListener) : RecyclerView.Adapter<BasisPengetahuanAdapter.BasisPengetahuanViewHolder>() {
    private lateinit var viewModel: BasisPengetahuanViewModel
    private val listBasisPengetahuan: ArrayList<BasisPengetahuanMaster> = ArrayList()

    init {
        val factory = ViewModelFactory.getInstance(fragmentActivity)
        viewModel = ViewModelProvider(fragment, factory).get(BasisPengetahuanViewModel::class.java)
    }
    inner class BasisPengetahuanViewHolder(private val binding: ItemBasisPengetahuanBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(basisPengetahuanMaster: BasisPengetahuanMaster){
            viewModel.getPenyakitByPenyakitId(basisPengetahuanMaster.penyakitId).observe(viewLifecycleOwner,{
                with(binding){
                    basisPengetahuanReceived.text = it.namaPenyakit

                    itemView.setOnClickListener {
                        val intent = Intent(itemView.context,RulesBasisPengetahuanActivity::class.java)
                        intent.putExtra(RulesBasisPengetahuanActivity.EXTRA_BASIS_PENGETAHUAN_ID,basisPengetahuanMaster.id)
                        itemView.context.startActivity(intent)
                    }

                    btnHapus.setOnClickListener {
                        listener.onDelete(basisPengetahuanMaster)
                    }

//                namaGejalaReceived.setOnClickListener {
//                    listener.onClick(gejala)
//                }
//                iconEdit.setOnClickListener {
//                    listener.onEdit(gejala)
//                }
//                iconDelete.setOnClickListener {
//                    listener.onDelete(gejala)
//                }

                }
            })
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): BasisPengetahuanViewHolder {
        val itemsBasisPengetahuan = ItemBasisPengetahuanBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup,false)
        return BasisPengetahuanViewHolder(itemsBasisPengetahuan)
    }

    override fun onBindViewHolder(holder: BasisPengetahuanViewHolder, position: Int) {
        holder.bind(listBasisPengetahuan[position])
    }

    override fun getItemCount(): Int = listBasisPengetahuan.size

    fun setData(basisPengetahuan: ArrayList<BasisPengetahuanMaster>) {
        listBasisPengetahuan.clear()
        listBasisPengetahuan.addAll(basisPengetahuan)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onDelete(basisPengetahuanMaster: BasisPengetahuanMaster)
    }


}