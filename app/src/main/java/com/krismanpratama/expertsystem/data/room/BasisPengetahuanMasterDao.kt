package com.krismanpratama.expertsystem.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Insert
import com.krismanpratama.expertsystem.data.entity.*

@Dao
interface BasisPengetahuanMasterDao{

    @Insert
    suspend fun addBasisPengetahuanMaster(basisPengetahuanMaster: BasisPengetahuanMaster): Long

    @Query("SELECT * FROM basis_pengetahuan_master")
    fun getAllBasisPengetahuanMaster(): LiveData<List<BasisPengetahuanMaster>>

    @Query("SELECT * FROM penyakit WHERE penyakit.id=:id")
    fun getPenyakitByPenyakitId(id: Int): LiveData<Penyakit>

    @Delete
    suspend fun deleteBasisPengetahuanMaster(basisPengetahuanMaster: BasisPengetahuanMaster)

    @Query("SELECT * FROM basis_pengetahuan_master WHERE basis_pengetahuan_master.penyakit_id=:id")
    fun getBasisPengetahuanMasterByPenyakitId(id: Int) : LiveData<BasisPengetahuanMaster>

    @Query("SELECT COUNT(*) FROM basis_pengetahuan_master WHERE basis_pengetahuan_master.penyakit_id = :id")
    suspend fun countBasisPengetahuanMasterByPenyakitId(id: Int) : Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRulesBasisPengetahuan(rulesBasisPengetahuan: RulesBasisPengetahuan)

    @Query("SELECT COUNT(*) FROM rules_basis_pengetahuan WHERE basis_pengetahuan_master_id = :idBasisPengetahuanMaster AND gejala_id= :idGejala")
    suspend fun countRulesOnBasisPengetahuanByGejalaId(idBasisPengetahuanMaster: Int,idGejala: Int) : Int

    @Transaction
    @Query("SELECT * FROM basis_pengetahuan_master WHERE id=:id")
    fun getBasisPengetahuanWithRules(id: Int): LiveData<BasisPengetahuanMasterWithRulesBasisPengetahuan>

    @Query("SELECT * FROM gejala WHERE gejala.id = :gejalaId")
    fun getGejalaByRule(gejalaId: Int): LiveData<Gejala>

    @Query("SELECT * FROM rules_basis_pengetahuan WHERE basis_pengetahuan_master_id = :idBasisPengetahuanMaster AND gejala_id = :idGejala")
    fun getRulesByGejalaId(idBasisPengetahuanMaster: Int, idGejala: Int) : LiveData<RulesBasisPengetahuan>

    @Update
    suspend fun updateRulesByGejalaId(rulesBasisPengetahuan: RulesBasisPengetahuan)



}