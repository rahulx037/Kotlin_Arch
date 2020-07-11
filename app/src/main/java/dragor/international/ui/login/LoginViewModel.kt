package dragor.international.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dragor.international.api.Resource
import dragor.international.api.models.LoginRequest
import dragor.international.api.models.LoginResponse
import dragor.international.api.models.RegisterRequest
import dragor.international.api.models.RegisterResponse
import dragor.international.repository.Repository

class LoginViewModel(private val loginRepository: Repository) : ViewModel() {

    fun doLogin(loginRequest: LoginRequest): LiveData<Resource<Resource<LoginResponse>>> {
        return loginRepository.doLogin(loginRequest)
    }

    fun registerUser(registerRequest: RegisterRequest): LiveData<Resource<Resource<RegisterResponse>>>{
        return loginRepository.registerUser(registerRequest)
    }

}
