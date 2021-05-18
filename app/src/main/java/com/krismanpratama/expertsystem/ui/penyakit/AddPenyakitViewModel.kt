package com.krismanpratama.expertsystem.ui.penyakit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.repository.SispakRepository

class AddPenyakitViewModel(private val mSispakRepository: SispakRepository) : ViewModel(){

    private var penyakitId = 0
    fun setSelectedPenyakit(penyakitId: Int){
        this.penyakitId = penyakitId
    }

    fun addPenyakit(penyakit: Penyakit){
        mSispakRepository.addPenyakit(penyakit)
    }

    fun updatePenyakit(penyakit: Penyakit){
        mSispakRepository.updatePenyakit(penyakit)
    }

    fun deletePenyakit(penyakit: Penyakit){
        mSispakRepository.deletePenyakit(penyakit)
    }

    fun getPenyakit(): LiveData<Penyakit>{
        return mSispakRepository.getPenyakit(penyakitId)
    }
}