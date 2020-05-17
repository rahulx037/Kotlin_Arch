package com.ehomosepian.club.repository

import androidx.lifecycle.LiveData
import com.ehomosepian.club.api.*
import com.ehomosepian.club.api.models.LoginRequest
import com.ehomosepian.club.api.models.LoginResponse
import com.ehomosepian.club.api.models.RegisterRequest
import com.ehomosepian.club.api.models.RegisterResponse
import com.ehomosepian.club.db.dao.UserDao
import com.ehomosepian.club.db.entity.DoctorsEntity
import com.ehomosepian.club.db.entity.User
import com.ehomosepian.club.util.AppExecutors
import com.ehomosepian.club.util.RateLimiter
import java.util.concurrent.TimeUnit

class Repository(
    private val api: APIService,
    private val userDao: UserDao,
    private val appExecutors: AppExecutors
) {
    fun loadUser(login: String): LiveData<Resource<User>> {
        return object : NetworkBoundResource<User, User>(appExecutors) {
            override fun saveCallResult(item: User) {
                userDao.insert(item)
            }

            override fun shouldFetch(data: User) = data == null

            override fun loadFromDb() = userDao.findByLogin(login)

            override fun createCall() = api.getUser(login)
        }.asLiveData()
    }


    fun doLogin(loginRequest: LoginRequest): LiveData<Resource<Resource<LoginResponse>>> {
        return object : OnlyNetworkResource<Resource<LoginResponse>>() {
             override fun createCall(): LiveData<ApiResponse<Resource<LoginResponse>>> {
                return api.dologin(loginRequest.mEmail, loginRequest.mPassword)
            }

        }.asLiveData()
    }

    fun registerUser(registerRequest: RegisterRequest): LiveData<Resource<Resource<RegisterResponse>>> {
        return object : OnlyNetworkResource<Resource<RegisterResponse>>() {
            override fun createCall(): LiveData<ApiResponse<Resource<RegisterResponse>>> {
                return api.registerUser(
                    registerRequest.first_name,
                    registerRequest.lastName,
                    registerRequest.email,
                    registerRequest.loginId,
                    registerRequest.password,
                    registerRequest.confirmPassword,
                    registerRequest.role
                )
            }

            override fun onFetchFailed() {
                super.onFetchFailed()
            }
        }.asLiveData()
    }

    fun nearbyDoctorsList(): LiveData<Resource<Resource<List<DoctorsEntity>>>> {
        return object :
            OnlyNetworkResource<Resource<List<DoctorsEntity>>>() {
            override fun createCall(): LiveData<ApiResponse<Resource<List<DoctorsEntity>>>> {
                return api.nearbyDoctorsList()
            }

            override fun onFetchFailed() {
                super.onFetchFailed()
            }
        }.asLiveData()
    }


    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.MINUTES)

    /*fun loadRepos(owner: String): LiveData<Resource<List<Repo>>> {
        return object : NetworkBoundResource<List<Repo>, List<Repo>>(appExecutors) {
            override fun saveCallResult(item: List<Repo>) {
                repoDao.insertRepos(item)
            }

            override fun shouldFetch(data: List<Repo>?): Boolean {
                return data == null || data.isEmpty() || repoListRateLimit.shouldFetch(owner)
            }

            override fun loadFromDb() = repoDao.loadRepositories(owner)

            override fun createCall() = githubService.getRepos(owner)

            override fun onFetchFailed() {
                repoListRateLimit.reset(owner)
            }
        }.asLiveData()
    }*/
}