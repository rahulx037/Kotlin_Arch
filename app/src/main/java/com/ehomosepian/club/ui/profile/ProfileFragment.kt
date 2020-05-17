package com.ehomosepian.club.ui.profile

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehomosepian.club.R
import com.ehomosepian.club.api.models.ProfileItem
import com.ehomosepian.club.databinding.FragmentProfileBinding
import com.ehomosepian.club.ui.base.BaseFragment
import com.ehomosepian.club.ui.profile.adapter.ProfileItemListner
import com.ehomosepian.club.ui.profile.adapter.ProfileRecyclerAdapter

class ProfileFragment : BaseFragment<FragmentProfileBinding>(), ProfileItemListner {

    private lateinit var adapter: ProfileRecyclerAdapter
    var data = mutableListOf<ProfileItem>()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_profile
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=ProfileRecyclerAdapter(this)
        dataBinding.profileRecycler.setLayoutManager(LinearLayoutManager(activity))
        dataBinding.profileRecycler.setHasFixedSize(true)
        dataBinding.profileRecycler.setAdapter(adapter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data.add(ProfileItem(title = "Wallet",sub_title = "Added Money,Rewards,etc."))
        data.add(ProfileItem(title = "Booking History",sub_title = "Health Checkup Appointments"))
        data.add(ProfileItem(title = "Family Members",sub_title = "Add family members at one place"))
        data.add(ProfileItem(title = "Talk to a Doctor-24x7",sub_title = "Doctor will help you at any time"))
        data.add(ProfileItem(title = "Leaderboard",sub_title = "View your health position in your circle"))
        data.add(ProfileItem(title = "Wearables",sub_title = "Track your healthy Activity"))

        adapter.setData(data)
    }


    override fun onListItemClick(profileItem: ProfileItem?) {
        when(profileItem){

        }
    }
}
