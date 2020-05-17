package com.ehomosepian.club.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.lang.reflect.Array.get

class ApiResponse<T> {

    @SerializedName(value = "data", alternate = ["device", "items"])
    @Expose
    var data: T? =null

    @SerializedName(value = "status", alternate = ["reason"])
    @Expose
    var status: String? = null

    @SerializedName("code")
    var code: Int = 0

    @SerializedName("message")
    @Expose
    val message: String? = null



    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            data = response.body()
            status = "success"
        } else {
            var message: String? = null
            if (response.errorBody() != null) {
                try {
                    message = response.errorBody().toString()
                } catch (ignored: IOException) {
                    Timber.e(ignored, "error while parsing response")
                }

            }
            if (message == null || message.trim { it <= ' ' }.isEmpty()) {
                message = response.message()
            }
            status = message
            data = null
        }

    }

    constructor(error: Throwable) {
        code = 500
        data = null
        status = error.message.toString()

    }

    fun isResponseSuccess(): Boolean {
        return status != null && status.equals("success", ignoreCase = true)
    }

    fun getResponseStatus(): String? {
        return status
    }

    val isSuccessful: Boolean
        get() = code in 200..299


}