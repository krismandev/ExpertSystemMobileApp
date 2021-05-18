package com.krismanpratama.expertsystem.repository

import androidx.lifecycle.LiveData
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.data.room.PenyakitDao
import com.krismanpratama.expertsystem.data.room.SispakDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PenyakitRepository(private val db: SispakDB) {

    companion object {
        @Volatile
        private var instance: PenyakitRepository? = null
        fun getInstance(sispakDB: SispakDB): PenyakitRepository =
            instance ?: synchronized(this) {
                instance ?: PenyakitRepository(sispakDB).apply { instance = this }
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