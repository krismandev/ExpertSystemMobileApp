package com.krismanpratama.expertsystem.helper

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.krismanpratama.expertsystem.repository.PenyakitRepository
import com.krismanpratama.expertsystem.ui.penyakit.AddPenyakitViewModel
import com.krismanpratama.expertsystem.ui.penyakit.PenyakitViewModel

class ViewModelFactory private constructor(private val mPenyakitRepository: PenyakitRepository) : ViewModelProvider.NewInstanceFactory() {
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
                return PenyakitViewModel(mPenyakitRepository) as T
            }
            modelClass.isAssignableFrom(AddPenyakitViewModel::class.java) -> {
                return AddPenyakitViewModel(mPenyakitRepository) as T
            }
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}