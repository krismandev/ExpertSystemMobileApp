package com.krismanpratama.expertsystem.repository

import androidx.lifecycle.LiveData
import com.krismanpratama.expertsystem.data.entity.*
import com.krismanpratama.expertsystem.data.room.BasisPengetahuanMasterDao
import com.krismanpratama.expertsystem.data.room.GejalaDao
import com.krismanpratama.expertsystem.data.room.PenyakitDao
import com.krismanpratama.expertsystem.data.room.SispakDB
import com.krismanpratama.expertsystem.helper.DataDummy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlinx.coroutines.launch
import kotlin.properties.Delegates


class SispakRepository(private val db: SispakDB) {

    companion object {
        @Volatile
        private var instance: SispakRepository? = null
        @InternalCoroutinesApi
        fun getInstance(sispakDB: SispakDB): SispakRepository =
            instance ?: synchronized(this) {
                instance ?: SispakRepository(sispakDB).apply { instance = this }
            }
    }

    private val mPenyakitDao: PenyakitDao
    private val mGejalaDao : GejalaDao
    private val mBasisPengetahuanMasterDao: BasisPengetahuanMasterDao
    init {
        mPenyakitDao = db.penyakitDao()
        mGejalaDao = db.gejalaDao()
        mBasisPengetahuanMasterDao = db.basisPengetahuanDao()
    }

    fun getAllPenyakit(): LiveData<List<Penyakit>> = mPenyakitDao.getAllPenyakit()
    fun getPenyakit(penyakitId: Int):LiveData<Penyakit> = mPenyakitDao.getPenyakit(penyakitId)

    fun addPenyakit(penyakit: Penyakit){
        CoroutineScope(Dispatchers.IO).launch {
            mPenyakitDao.addPenyakit(penyakit)
        }
    }

    fun deletePenyakit(penyakit: Penyakit){
        CoroutineScope(Dispatchers.IO).launch {
            mPenyakitDao.deletePenyakit(penyakit)
        }
    }

    fun updatePenyakit(penyakit: Penyakit){
        CoroutineScope(Dispatchers.IO).launch {
            mPenyakitDao.updatePenyakit(penyakit)
        }
    }
    fun getAllGejala(): LiveData<List<Gejala>> = mGejalaDao.getAllGejala()
    fun getGejala(gejalaId: Int):LiveData<Gejala> = mGejalaDao.getGejala(gejalaId)

    fun addGejala(gejala: Gejala){
        CoroutineScope(Dispatchers.IO).launch {
            mGejalaDao.addGejala(gejala)
        }
    }

    fun deleteGejala(gejala: Gejala){
        CoroutineScope(Dispatchers.IO).launch {
            mGejalaDao.deleteGejala(gejala)
        }
    }

    fun updateGejala(gejala: Gejala){
        CoroutineScope(Dispatchers.IO).launch {
            mGejalaDao.updateGejala(gejala)
        }
    }

    fun getAllBasisPengetahuanMaster() : LiveData<List<BasisPengetahuanMaster>> = mBasisPengetahuanMasterDao.getAllBasisPengetahuanMaster()

    suspend fun addBasisPengetahuanMaster(basisPengetahuanMaster: BasisPengetahuanMaster): Long{
        return mBasisPengetahuanMasterDao.addBasisPengetahuanMaster(basisPengetahuanMaster)
    }

    fun getBasisPengetahuanMasterByPenyakitId(id: Int): LiveData<BasisPengetahuanMaster> = mBasisPengetahuanMasterDao.getBasisPengetahuanMasterByPenyakitId(id)

    fun deleteBasisPengetahuanMaster(basisPengetahuanMaster: BasisPengetahuanMaster){
        CoroutineScope(Dispatchers.IO).launch {
            mBasisPengetahuanMasterDao.deleteBasisPengetahuanMaster(basisPengetahuanMaster)
        }
    }

    fun countBasisPengetahuanByPenyakitId(id:Int) : Int{
        var num = 0
        CoroutineScope(Dispatchers.IO).launch {
            num = mBasisPengetahuanMasterDao.countBasisPengetahuanMasterByPenyakitId(id)
        }
        return num
    }

    fun addRulesBasisPengetahuan(rulesBasisPengetahuan: RulesBasisPengetahuan){
        CoroutineScope(Dispatchers.IO).launch {
            mBasisPengetahuanMasterDao.addRulesBasisPengetahuan(rulesBasisPengetahuan)
        }
    }

    fun countRulesOnBasisPengetahuanByGejalaId(idBasisPengetahuanMaster:Int,idGejala: Int) : Int{
        var num = 0
        CoroutineScope(Dispatchers.IO).launch {
            num = mBasisPengetahuanMasterDao.countRulesOnBasisPengetahuanByGejalaId(idBasisPengetahuanMaster,idGejala)
        }
        return num
    }

    fun getBasisPengetahuanWithRules(id: Int): LiveData<BasisPengetahuanMasterWithRulesBasisPengetahuan> = mBasisPengetahuanMasterDao.getBasisPengetahuanWithRules(id)

    fun getGejalaByRule(gejalaId: Int): LiveData<Gejala> = mBasisPengetahuanMasterDao.getGejalaByRule(gejalaId)

    fun getRulesByGejalaId(idBasisPengetahuanMaster: Int, idGejala: Int) : LiveData<RulesBasisPengetahuan> = mBasisPengetahuanMasterDao.getRulesByGejalaId(idBasisPengetahuanMaster,idGejala)

    fun updateRulesByGejalaId(rulesBasisPengetahuan: RulesBasisPengetahuan){
        CoroutineScope(Dispatchers.IO).launch {
            mBasisPengetahuanMasterDao.updateRulesByGejalaId(rulesBasisPengetahuan)
        }
    }

    fun generateListPenyakit(){
        val listPenyakit = ArrayList<Penyakit>()
        val generatePenyakit = DataDummy.generateDummyPenyakit()

        listPenyakit.addAll(generatePenyakit)
        CoroutineScope(Dispatchers.IO).launch {
            mPenyakitDao.addListPenyakit(listPenyakit)
        }
    }

    fun generateListGejala(){
        val listGejala = ArrayList<Gejala>()
        val generateGejala = DataDummy.generateDummyGejala()

        listGejala.addAll(generateGejala)
        CoroutineScope(Dispatchers.IO).launch {
            mGejalaDao.addListGejala(listGejala)
        }
    }
}