package com.ehomosepian.club.api.models

import com.google.gson.annotations.SerializedName

data class ProfileItem(
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("sub_title")
    var sub_title: String? = null,

    @SerializedName("image")
    var image: String? = null
)