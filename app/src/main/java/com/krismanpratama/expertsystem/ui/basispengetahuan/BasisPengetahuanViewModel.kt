package com.krismanpratama.expertsystem.ui.basispengetahuan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMasterWithRulesBasisPengetahuan
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.repository.SispakRepository

class BasisPengetahuanViewModel(private val mSispakRepository: SispakRepository) : ViewModel() {

    fun getAllBasisPengetahuanMaster() : LiveData<List<BasisPengetahuanMaster>> = mSispakRepository.getAllBasisPengetahuanMaster()

    fun getPenyakitByPenyakitId(id: Int): LiveData<Penyakit> = mSispakRepository.getPenyakit(id)

    fun getBasisPengetahuanWithRules(id: Int) : LiveData<BasisPengetahuanMasterWithRulesBasisPengetahuan> = mSispakRepository.getBasisPengetahuanWithRules(id)

    fun getGejalaByRule(gejalaId: Int): LiveData<Gejala> = mSispakRepository.getGejalaByRule(gejalaId)

    fun deleteBasisPengetahuanMaster(basisPengetahuanMaster: BasisPengetahuanMaster) = mSispakRepository.deleteBasisPengetahuanMaster(basisPengetahuanMaster)

//    fun getPenyakitByPenyakitId(penyakitId: Int): LiveData<Penyakit> = mSispakRepository.getPenyakit(penyakitId)
}