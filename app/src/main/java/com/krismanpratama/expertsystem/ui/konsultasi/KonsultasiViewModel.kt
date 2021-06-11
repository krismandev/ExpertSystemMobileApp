package com.krismanpratama.expertsystem.ui.konsultasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMasterWithRulesBasisPengetahuan
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.repository.SispakRepository

class KonsultasiViewModel(private val mSispakRepository: SispakRepository) : ViewModel() {
    fun getAllBasisPengetahuanMaster(): LiveData<List<BasisPengetahuanMaster>> = mSispakRepository.getAllBasisPengetahuanMaster()

    fun getPenyakitByPenyakitId(penyakitId: Int): LiveData<Penyakit> = mSispakRepository.getPenyakit(penyakitId)

    fun getAllGejala() : LiveData<List<Gejala>> = mSispakRepository.getAllGejala()

    fun getBasisPengetahuanWithRules(id: Int) : LiveData<BasisPengetahuanMasterWithRulesBasisPengetahuan> = mSispakRepository.getBasisPengetahuanWithRules(id)
}