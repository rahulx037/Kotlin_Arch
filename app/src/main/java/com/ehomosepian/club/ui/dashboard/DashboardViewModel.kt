package com.ehomosepian.club.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.ehomosepian.club.api.Resource
import com.ehomosepian.club.db.entity.User
import com.ehomosepian.club.repository.Repository
import com.ehomosepian.club.util.AbsentLiveData

class DashboardViewModel(private val repo: Repository) : ViewModel() {
    private val _login = MutableLiveData<String?>()
    val login: LiveData<String?>
        get() = _login


    fun setLogin(login: String?) {
        if (_login.value != login) {
            _login.value = login
        }
    }

    fun retry() {
        _login.value?.let {
            _login.value = it
        }
    }


    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}