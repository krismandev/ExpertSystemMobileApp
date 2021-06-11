package com.krismanpratama.expertsystem.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.krismanpratama.expertsystem.repository.SispakRepository

class HomeViewModel(private val mSispakRepository: SispakRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}