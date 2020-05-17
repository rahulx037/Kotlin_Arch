package com.ehomosepian.club.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ehomosepian.club.R
import com.ehomosepian.club.databinding.FragmentHomeBinding
import com.ehomosepian.club.db.entity.DoctorsEntity
import com.ehomosepian.club.ui.base.BaseFragment
import com.ehomosepian.club.ui.home.adapters.DoctorRecyclerAdapter
import com.ehomosepian.club.ui.home.listners.DoctorListClickItem
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(),DoctorListClickItem {

    private  val homeViewModel: HomeViewModel by viewModel()

    private lateinit var adapter:DoctorRecyclerAdapter
    var data = mutableListOf<DoctorsEntity>()

    override fun getLayoutRes(): Int {
        return R.layout.fragment_home
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter=DoctorRecyclerAdapter(this)

        dataBinding.docsRecycler.setLayoutManager(LinearLayoutManager(activity))
        dataBinding.docsRecycler.setHasFixedSize(true)
        dataBinding.docsRecycler.setAdapter(adapter)

        /*homeViewModel.text.observe(viewLifecycleOwner, Observer {
        })*/
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        data.add(DoctorsEntity(category = "Cardiologist", firstName = "Rahul Singh", timing =  "10:00Am-12:00Pm"))
        data.add(DoctorsEntity(category = "Anesthesiologist",firstName =  "Lokesh", timing = "10:00Am-12:00Pm"))
        data.add(DoctorsEntity(category = "Chiropractor",firstName =  "Sandeep", timing = "10:00Am-12:00Pm"))
        data.add(DoctorsEntity(category = "Physiotherapist",firstName =  "Rakesh", timing = "10:00Am-12:00Pm"))
        data.add(DoctorsEntity(category = "Orthologist", firstName = "Vijay", timing = "10:00Am-12:00Pm"))
        data.add(DoctorsEntity(category = "Cardiologist",firstName =  "Sudhanshu",timing =  "10:00Am-12:00Pm"))
        adapter.setData(data)
    }

    override fun onListItemClick(doctorentity: DoctorsEntity?) {
        TODO("Not yet implemented")
    }


}
