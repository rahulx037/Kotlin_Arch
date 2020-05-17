package com.ehomosepian.club.ui.profile.adapter

import com.ehomosepian.club.api.models.ProfileItem

interface ProfileItemListner {
    abstract fun onListItemClick(profileItem: ProfileItem?)

}