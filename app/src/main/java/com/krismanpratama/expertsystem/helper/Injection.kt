package com.krismanpratama.expertsystem.helper

import android.content.Context
import com.krismanpratama.expertsystem.data.room.SispakDB
import com.krismanpratama.expertsystem.repository.SispakRepository
import kotlinx.coroutines.InternalCoroutinesApi

object Injection {
    @InternalCoroutinesApi
    fun provideRepository(context: Context): SispakRepository {

        val database = SispakDB.getDatabase(context)

        return SispakRepository.getInstance(database)
    }
}