package com.krismanpratama.expertsystem.helper

import android.content.Context
import com.krismanpratama.expertsystem.data.room.SispakDB
import com.krismanpratama.expertsystem.repository.SispakRepository

object Injection {
    fun provideRepository(context: Context): SispakRepository {

        val database = SispakDB.getDatabase(context)

        return SispakRepository.getInstance(database)
    }
}