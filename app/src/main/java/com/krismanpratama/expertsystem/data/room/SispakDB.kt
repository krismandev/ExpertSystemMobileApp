package com.krismanpratama.expertsystem.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.krismanpratama.expertsystem.data.entity.Penyakit
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Penyakit::class], version = 1)
abstract class SispakDB: RoomDatabase() {
    abstract fun penyakitDao(): PenyakitDao

    companion object{
        @Volatile
        private var INSTANCE: SispakDB? = null

        @JvmStatic
        fun getDatabase(context: Context): SispakDB {
            if (INSTANCE == null) {
                synchronized(SispakDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SispakDB::class.java, "note_database")
                        .build()

                }
            }
            return INSTANCE as SispakDB
        }
    }

    abstract fun gejalaDao(): GejalaDao

    companion object{
        @Volatile
        private var INSTANCE: SispakDB? = null

        @JvmStatic
        fun getDatabase(context: Context): SispakDB {
            if (INSTANCE == null) {
                synchronized(SispakDB::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        SispakDB::class.java, "note_database")
                        .build()

                }
            }
            return INSTANCE as SispakDB
        }
    }
}