package dragor.international.ui.profile.adapter

import dragor.international.api.models.ProfileItem


interface ProfileItemListner {
    abstract fun onListItemClick(profileItem: ProfileItem?)

}