package com.ehomosepian.club.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehomosepian.club.repository.Repository
import com.ehomosepian.club.ui.base.BaseViewModel

class ProfileViewModel(private val repo: Repository) : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}