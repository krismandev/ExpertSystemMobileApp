package com.krismanpratama.expertsystem.ui.gejala

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.repository.SispakRepository

class GejalaViewModel(private val mSispakRepository: SispakRepository): ViewModel() {
    fun getAllGejala() : LiveData<List<Gejala>> = mSispakRepository.getAllGejala()

    fun deleteGejala(gejala: Gejala) = mSispakRepository.deleteGejala(gejala)
}