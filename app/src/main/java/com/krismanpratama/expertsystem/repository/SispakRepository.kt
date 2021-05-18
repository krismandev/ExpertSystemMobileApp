package com.krismanpratama.expertsystem.repository

import androidx.lifecycle.LiveData
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.data.room.PenyakitDao
import com.krismanpratama.expertsystem.data.room.SispakDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SispakRepository(private val db: SispakDB) {

    companion object {
        @Volatile
        private var instance: SispakRepository? = null
        fun getInstance(sispakDB: SispakDB): SispakRepository =
            instance ?: synchronized(this) {
                instance ?: SispakRepository(sispakDB).apply { instance = this }
            }
    }

    private val mPenyakitDao: PenyakitDao
    init {
        mPenyakitDao = db.penyakitDao()
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
}