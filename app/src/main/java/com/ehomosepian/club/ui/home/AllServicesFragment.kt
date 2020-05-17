package com.ehomosepian.club.ui.home

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ehomosepian.club.R
import com.ehomosepian.club.databinding.ServicesFragmentBinding
import com.ehomosepian.club.ui.base.BaseFragment
import java.util.*

class AllServicesFragment :BaseFragment<ServicesFragmentBinding>() {

    private val tabIcons = intArrayOf(
        R.drawable.ic_email_black_24dp
    )
    override fun getLayoutRes(): Int {
       return R.layout.services_fragment
    }

    companion object{
        fun newInstance(): AllServicesFragment? {
            return AllServicesFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        /*(activity as AppCompatActivity?)!!.setSupportActionBar(dataBinding.toolbar)
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)!!.supportActionBar
            .setHomeAsUpIndicator(R.drawable.profile)*/
        setupViewPager(dataBinding.viewpager)
        dataBinding.tabs.setupWithViewPager(dataBinding.viewpager)
        //setupTabIcons()
        Objects.requireNonNull(dataBinding.tabs.getTabAt(0))?.select()
    }

    private fun setupTabIcons() {
        dataBinding.tabs.getTabAt(0)?.setIcon(tabIcons[0])
        dataBinding.tabs.getTabAt(0)?.icon?.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter: ViewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFragment(HomeFragment(), "Physicians")
        adapter.addFragment(HomeFragment(), "Insurence")
        adapter.addFragment(HomeFragment(), "University")
        adapter.addFragment(HomeFragment(), "Stores")
        viewPager.adapter = adapter
    }

    internal class ViewPagerAdapter(manager: FragmentManager?) :
        FragmentPagerAdapter(manager!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(
            fragment: Fragment,
            title: String
        ) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }

}