package com.krismanpratama.expertsystem.ui.gejala

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.repository.SispakRepository

class AddGejalaViewModel(private val mSispakRepository: SispakRepository) : ViewModel(){

    private var gejalaId = 0
    fun setSelectedGejala(gejalaId: Int){
        this.gejalaId = gejalaId
    }

    fun addGejala(gejala: Gejala){
        mSispakRepository.addGejala(gejala)
    }

    fun updateGejala(gejala: Gejala){
        mSispakRepository.updateGejala(gejala)
    }

    fun deleteGejala(gejala: Gejala){
        mSispakRepository.deleteGejala(gejala)
    }

    fun getGejala(): LiveData<Gejala> {
        return mSispakRepository.getGejala(gejalaId)
    }
}