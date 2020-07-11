/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dragor.international.api

import androidx.lifecycle.LiveData
import dragor.international.api.models.LoginResponse
import dragor.international.api.models.RegisterResponse
import dragor.international.db.entity.DoctorsEntity
import dragor.international.db.entity.User
import retrofit2.http.*

/**
 * REST API access points
 */
interface APIService {
    @GET("users/{etete}")
    fun getUser(@Path("login") login: String): LiveData<ApiResponse<User>>

    @FormUrlEncoded
    @POST("login")
   // @Headers("Content-Type: application/json")
    fun  dologin(
        @Field("login_id") email: String,
        @Field("password") password: String
    ): LiveData<ApiResponse<Resource<LoginResponse>>>

    @FormUrlEncoded
    @POST("registerUser")
    fun registerUser(
        @Field("first_name") firstName: String?,
        @Field("last_name") lastName: String?,
        @Field("email") email: String?,
        @Field("login_id") loginId: String?,
        @Field("password") password: String?,
        @Field("c_password") confirmPassword: String?,
        @Field("role") role: String?
    ): LiveData<ApiResponse<Resource<RegisterResponse>>>


    @POST("doctors")
    fun nearbyDoctorsList(): LiveData<ApiResponse<Resource<List<DoctorsEntity>>>>


    /*@GET("users/{login}/repos")
    fun getRepos(@Path("login") login: String): LiveData<ApiResponse<List<Repo>>>

    @GET("repos/{owner}/{name}")
    fun getRepo(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<Repo>>

    @GET("repos/{owner}/{name}/contributors")
    fun getContributors(
        @Path("owner") owner: String,
        @Path("name") name: String
    ): LiveData<ApiResponse<List<Contributor>>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String): LiveData<ApiResponse<RepoSearchResponse>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") query: String, @Query("page") page: Int): Call<RepoSearchResponse>*/
}
