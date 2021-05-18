package com.krismanpratama.expertsystem.ui.penyakit

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.repository.SispakRepository

class PenyakitViewModel(private val mSispakRepository: SispakRepository) : ViewModel() {

    fun getAllPenyakit() : LiveData<List<Penyakit>> = mSispakRepository.getAllPenyakit()

    fun deletePenyakit(penyakit: Penyakit) = mSispakRepository.deletePenyakit(penyakit)

}