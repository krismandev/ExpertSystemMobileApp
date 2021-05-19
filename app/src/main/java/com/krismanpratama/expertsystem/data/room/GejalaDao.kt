package com.krismanpratama.expertsystem.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit

@Dao
interface GejalaDao {
    @Insert
    suspend fun addGejala(gejala: Gejala)

    @Update
    suspend fun updateGejala(gejala: Gejala)

    @Delete
    suspend fun deleteGejala(gejala: Gejala)

    @Query("SELECT * FROM gejala")
    fun getAllGejala(): LiveData<List<Gejala>>

    @Query("SELECT * FROM gejala WHERE gejala.id= :id")
    fun getGejala(id: Int): LiveData<Gejala>
}