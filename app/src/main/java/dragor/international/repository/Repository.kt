package dragor.international.repository

import androidx.lifecycle.LiveData
import dragor.international.api.*
import dragor.international.api.models.LoginRequest
import dragor.international.api.models.LoginResponse
import dragor.international.api.models.RegisterRequest
import dragor.international.api.models.RegisterResponse
import dragor.international.db.dao.UserDao
import dragor.international.db.entity.DoctorsEntity
import dragor.international.db.entity.User
import dragor.international.util.AppExecutors
import dragor.international.util.RateLimiter
import java.util.concurrent.TimeUnit

class Repository (
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