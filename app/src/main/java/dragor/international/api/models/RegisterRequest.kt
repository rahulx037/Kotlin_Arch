package dragor.international.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @SerializedName("first_name")
    
    var first_name: String?,
    @SerializedName("last_name")
    
    val lastName: String?,

    @SerializedName("role")
    
    val role: String?,

    @SerializedName("mobile")
    
    val mobile: String?,

    @SerializedName("email")
    
    val email: String?,

    @SerializedName("Password")
    
    val password: String?,

    @SerializedName("c_password")
    
    val confirmPassword: String?,

    @SerializedName("login_id")
    
    val loginId: String?
)