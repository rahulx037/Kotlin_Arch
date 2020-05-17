package com.ehomosepian.club.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ehomosepian.club.api.Resource
import com.ehomosepian.club.api.models.LoginRequest
import com.ehomosepian.club.api.models.LoginResponse
import com.ehomosepian.club.api.models.RegisterRequest
import com.ehomosepian.club.api.models.RegisterResponse
import com.ehomosepian.club.repository.Repository

class LoginViewModel(private val loginRepository: Repository) : ViewModel() {

    fun doLogin(loginRequest: LoginRequest): LiveData<Resource<Resource<LoginResponse>>> {
        return loginRepository.doLogin(loginRequest)
    }

    fun registerUser(registerRequest: RegisterRequest): LiveData<Resource<Resource<RegisterResponse>>>{
        return loginRepository.registerUser(registerRequest)
    }

}
