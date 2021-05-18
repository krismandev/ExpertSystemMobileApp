package com.krismanpratama.expertsystem.ui.penyakit

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.repository.PenyakitRepository

class AddPenyakitViewModel(private val mPenyakitRepository: PenyakitRepository) : ViewModel(){

    private var penyakitId = 0
    fun setSelectedPenyakit(penyakitId: Int){
        this.penyakitId = penyakitId
    }

    fun addPenyakit(penyakit: Penyakit){
        mPenyakitRepository.addPenyakit(penyakit)
    }

    fun updatePenyakit(penyakit: Penyakit){
        mPenyakitRepository.updatePenyakit(penyakit)
    }

    fun deletePenyakit(penyakit: Penyakit){
        mPenyakitRepository.deletePenyakit(penyakit)
    }

    fun getPenyakit(): LiveData<Penyakit>{
        return mPenyakitRepository.getPenyakit(penyakitId)
    }
}