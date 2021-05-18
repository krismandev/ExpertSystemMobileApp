package com.krismanpratama.expertsystem.helper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.repository.SispakRepository
import com.krismanpratama.expertsystem.ui.penyakit.AddPenyakitViewModel
import com.krismanpratama.expertsystem.ui.penyakit.PenyakitViewModel

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
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}