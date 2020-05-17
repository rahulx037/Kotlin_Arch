package com.ehomosepian.club.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehomosepian.club.R
import com.ehomosepian.club.api.models.ProfileItem
import com.ehomosepian.club.databinding.FragmentDashboardBinding
import com.ehomosepian.club.ui.base.BaseFragment
import com.ehomosepian.club.ui.profile.adapter.ProfileItemListner
import com.ehomosepian.club.ui.profile.adapter.ProfileRecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(), ProfileItemListner {

    private val dashboardViewModel by viewModel<DashboardViewModel>()
    private lateinit var adapter: ProfileRecyclerAdapter
    var data = mutableListOf<ProfileItem>()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_dashboard
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=ProfileRecyclerAdapter(this)
        dataBinding.healthRecycler.setLayoutManager(LinearLayoutManager(activity))
        dataBinding.healthRecycler.setHasFixedSize(true)
        dataBinding.healthRecycler.setAdapter(adapter)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data.add(ProfileItem(title = "Vaccinations",sub_title = "Keep your kids health"))
        data.add(ProfileItem(title = "Insights",sub_title = "View your health score & insights"))
        data.add(ProfileItem(title = "Lab Results",sub_title = "Your all lab results"))
        data.add(ProfileItem(title = "Diet plan",sub_title = "Your diet plan details"))
        adapter.setData(data)
    }




    override fun onListItemClick(profileItem: ProfileItem?) {
        when(profileItem){

        }
    }
}
