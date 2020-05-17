package com.ehomosepian.club.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("token")
    
    private var token: String?,
    @SerializedName("first_name")
    
    private val firstName: String?

)