package dragor.international.db.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DoctorsEntity(
    @SerializedName("id")
    val id: Int=0,
    @SerializedName("first_name")
    val firstName: String ="",
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("email")
    val email: String? =null,
    @SerializedName("address")
    val address: String? =null,
    @SerializedName("phone")
    val phone: String?=null,
    @SerializedName("latitude")
    val latitude: String?=null,
    @SerializedName("longitude")
    val longitude: String?=null,
    @SerializedName("status")
    val status: String?=null,
    @SerializedName("clinic_timings")
    val timing: String?=null,
    @SerializedName("image")
    val avtar: String?=null
)