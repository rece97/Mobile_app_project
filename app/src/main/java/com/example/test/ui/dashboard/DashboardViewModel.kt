package com.example.test.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    val selected = MutableLiveData<Bug>()

    fun select(bug: Bug){
        selected.value = bug
    }

}