package com.krismanpratama.expertsystem.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krismanpratama.expertsystem.data.entity.BasisPengetahuanMaster
import com.krismanpratama.expertsystem.data.entity.Gejala
import com.krismanpratama.expertsystem.data.entity.Penyakit
import com.krismanpratama.expertsystem.data.entity.RulesBasisPengetahuan
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Penyakit::class, Gejala::class, BasisPengetahuanMaster::class, RulesBasisPengetahuan::class], version = 2)
abstract class SispakDB: RoomDatabase() {
    abstract fun penyakitDao(): PenyakitDao
    abstract fun gejalaDao(): GejalaDao
    abstract fun basisPengetahuanDao(): BasisPengetahuanMasterDao

    companion object{
        @Volatile
        private var INSTANCE: SispakDB? = null

        @InternalCoroutinesApi
        @JvmStatic
        fun getDatabase(context: Context): SispakDB {
            if (INSTANCE == null) {
                synchronized(SispakDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SispakDB::class.java, "sispak.db")
                        .build()

                }
            }
            return INSTANCE as SispakDB
        }
    }
}