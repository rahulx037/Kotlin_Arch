package dragor.international.api.models

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("login_id")
    val mEmail: String ="",

    @SerializedName("password")
    val mPassword: String  =""
)