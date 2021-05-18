package com.krismanpratama.expertsystem.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.krismanpratama.expertsystem.data.entity.Penyakit

@Dao
interface PenyakitDao {
    @Insert
    suspend fun addPenyakit(penyakit: Penyakit)

    @Update
    suspend fun updatePenyakit(penyakit: Penyakit)

    @Delete
    suspend fun deletePenyakit(penyakit: Penyakit)

    @Query("SELECT * FROM penyakit")
    fun getAllPenyakit(): LiveData<List<Penyakit>>

    @Query("SELECT * FROM penyakit WHERE penyakit.id= :id")
    fun getPenyakit(id: Int): LiveData<Penyakit>
}