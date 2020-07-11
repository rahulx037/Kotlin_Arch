package dragor.international.api.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("id")
    private val id: Int? =0,

    @SerializedName("first_name")
    private val firstName: String? ="",

    @SerializedName("last_name")
    private val lastName: String?="",

    @SerializedName("email")
    private val email: String?="",

    @SerializedName("email_verified_at")
    private val emailVerifiedAt: String?="",

    @SerializedName("role")
    private val role: String?= "",

    @SerializedName("phone")
    private val phone: String?="",

    @SerializedName("address1")
    private val address1: String?="",

    @SerializedName("address2")
    private val address2: String?="",

    @SerializedName("zip")
    private val zip: String?="",

    @SerializedName("login_id")
    private val loginId: String?="",

    @SerializedName("status")
    private val status: String?="",

    @SerializedName("created_at")
    private val createdAt: String?="",

    @SerializedName("updated_at")
    private val updatedAt: String?="",

    @SerializedName("token")
    private val token: String? = ""

)