package dragor.international.ui.home.listners

import dragor.international.db.entity.DoctorsEntity


interface DoctorListClickItem {
    abstract fun onListItemClick(doctorentity: DoctorsEntity?)

}