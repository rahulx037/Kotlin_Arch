package com.ehomosepian.club.ui.home.listners

import com.ehomosepian.club.db.entity.DoctorsEntity

interface DoctorListClickItem {
    abstract fun onListItemClick(doctorentity: DoctorsEntity?)

}