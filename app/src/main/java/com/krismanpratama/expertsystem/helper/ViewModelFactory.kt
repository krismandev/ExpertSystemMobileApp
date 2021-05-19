package com.krismanpratama.expertsystem.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.repository.SispakRepository
import com.krismanpratama.expertsystem.ui.gejala.AddGejalaViewModel
import com.krismanpratama.expertsystem.ui.gejala.GejalaViewModel
import com.krismanpratama.expertsystem.ui.penyakit.AddPenyakitViewModel
import com.krismanpratama.expertsystem.ui.penyakit.PenyakitViewModel
import kotlinx.coroutines.internal.synchronized

class ViewModelFactory private constructor(private val mSispakRepository: SispakRepository) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(PenyakitViewModel::class.java) -> {
                return PenyakitViewModel(mSispakRepository) as T
            }
            modelClass.isAssignableFrom(AddPenyakitViewModel::class.java) -> {
                return AddPenyakitViewModel(mSispakRepository) as T
            }
            modelClass.isAssignableFrom(GejalaViewModel::class.java) -> {
                return GejalaViewModel(mSispakRepository) as T
            }
            modelClass.isAssignableFrom(AddGejalaViewModel::class.java) -> {
                return AddGejalaViewModel(mSispakRepository) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}