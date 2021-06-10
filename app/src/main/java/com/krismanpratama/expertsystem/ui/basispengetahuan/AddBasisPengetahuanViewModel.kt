package com.krismanpratama.expertsystem.ui.basispengetahuan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.data.entity.RulesBasisPengetahuan
import com.krismanpratama.expertsystem.repository.SispakRepository
import kotlinx.coroutines.launch

class AddBasisPengetahuanViewModel(private val mSispakRepository: SispakRepository): ViewModel() {

    fun getAllPenyakit() : LiveData<List<Penyakit>> = mSispakRepository.getAllPenyakit()

    fun getAllGejala() : LiveData<List<Gejala>> = mSispakRepository.getAllGejala()

    suspend fun addBasisPengetahuanMaster(basisPengetahuanMaster: BasisPengetahuanMaster) = mSispakRepository.addBasisPengetahuanMaster(basisPengetahuanMaster)

    fun getBasisPengetahuanMasterByPenyakitId(id: Int): LiveData<BasisPengetahuanMaster> = mSispakRepository.getBasisPengetahuanMasterByPenyakitId(id)

    fun countBasisPengetahuanByPenyakitId(id: Int): Int = mSispakRepository.countBasisPengetahuanByPenyakitId(id)

    fun addRulesBasisPengetahuan(rulesBasisPengetahuan: RulesBasisPengetahuan) = mSispakRepository.addRulesBasisPengetahuan(rulesBasisPengetahuan)

    fun countRulesOnBasisPengetahuanByGejalaId(idBasisPengetahuanMaster: Int, idGejala: Int): Int = mSispakRepository.countRulesOnBasisPengetahuanByGejalaId(idBasisPengetahuanMaster,idGejala)

    fun getRulesByGejalaId(idBasisPengetahuanMaster: Int, idGejala: Int) : LiveData<RulesBasisPengetahuan> = mSispakRepository.getRulesByGejalaId(idBasisPengetahuanMaster,idGejala)

    fun updateRulesByGejalaId(rulesBasisPengetahuan: RulesBasisPengetahuan) = mSispakRepository.updateRulesByGejalaId(rulesBasisPengetahuan)
}