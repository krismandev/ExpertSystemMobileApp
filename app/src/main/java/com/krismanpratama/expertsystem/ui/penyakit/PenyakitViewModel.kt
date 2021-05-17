package com.krismanpratama.expertsystem.ui.penyakit

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.repository.PenyakitRepository

class PenyakitViewModel(private val mPenyakitRepository: PenyakitRepository) : ViewModel() {

    fun getAllPenyakit() : LiveData<List<Penyakit>> = mPenyakitRepository.getAllPenyakit()

}