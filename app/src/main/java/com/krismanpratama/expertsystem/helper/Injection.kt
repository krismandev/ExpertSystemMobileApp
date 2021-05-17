package com.krismanpratama.expertsystem.helper

import android.content.Context
import com.krismanpratama.expertsystem.data.room.SispakDB
import com.krismanpratama.expertsystem.repository.PenyakitRepository

object Injection {
    fun provideRepository(context: Context): PenyakitRepository {

        val database = SispakDB.getDatabase(context)

        return PenyakitRepository.getInstance(database)
    }
}