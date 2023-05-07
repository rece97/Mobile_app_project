package com.example.test.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FishViewModel : ViewModel() {
    val selected = MutableLiveData<Fish>()

    fun select(fish: Fish){
        selected.value = fish
    }
}