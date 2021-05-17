package com.krismanpratama.expertsystem.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
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

    fun getAllPenyakit(): LiveData<List<Penyakit>> = mPenyakitDao.getPenyakit()

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