package com.ehomosepian.club.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ehomosepian.club.R
import com.ehomosepian.club.databinding.RecyclerDashboardItemBinding
import com.ehomosepian.club.db.entity.DoctorsEntity
import com.ehomosepian.club.ui.base.BaseAdapter
import com.ehomosepian.club.ui.home.listners.DoctorListClickItem
import java.lang.String
import java.util.*

class DoctorRecyclerAdapter(@NonNull private val clickListner: DoctorListClickItem ) : BaseAdapter<DoctorRecyclerAdapter.ItemsViewHolder,DoctorsEntity>(),
    Filterable {
    var mItemsEntities = mutableListOf<DoctorsEntity>()
    var mItemsEntitiesFiltered = mutableListOf<DoctorsEntity>()

    private var layoutInflater: LayoutInflater? = null

    private var mMenuItemsListCallback: DoctorListClickItem? = null
    private lateinit var binding: RecyclerDashboardItemBinding

    init {
        mItemsEntities = ArrayList()
        mItemsEntitiesFiltered = ArrayList()
        mMenuItemsListCallback=clickListner
    }



    override fun setData(data: List<DoctorsEntity>) {
        mItemsEntities = data.toMutableList()
        mItemsEntitiesFiltered = data.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext())
        }
        binding = DataBindingUtil.inflate(
            layoutInflater!!,
            R.layout.recycler_dashboard_item,
            parent,
            false
        )
        return ItemsViewHolder(binding, mMenuItemsListCallback)
    }

    override fun getItemCount(): Int {
        return mItemsEntitiesFiltered.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.onBind(mItemsEntitiesFiltered[position])
    }

    public class ItemsViewHolder(
        val itemsbinding: RecyclerDashboardItemBinding,
        callback: DoctorListClickItem?
    ) :
        RecyclerView.ViewHolder(itemsbinding.root) {
        fun onBind(itementity: DoctorsEntity?) {
            itemsbinding.doctor = itementity
            itemsbinding.executePendingBindings()
        }

        init {
            itemsbinding.info.setOnClickListener { view -> callback?.onListItemClick(itemsbinding.doctor) }
        }
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                mItemsEntitiesFiltered = if (charString.isEmpty()) {
                    mItemsEntities
                } else {
                    val filteredList: MutableList<DoctorsEntity> = ArrayList<DoctorsEntity>()
                    for (row in mItemsEntities) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (String.valueOf(row.category) == charString || row.firstName
                                .equals(charString)
                        ) {
                            filteredList.add(row)
                        }
                    }
                    filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = mItemsEntitiesFiltered
                return filterResults
            }

            override fun publishResults(
                charSequence: CharSequence,
                filterResults: FilterResults
            ) {
                mItemsEntitiesFiltered = filterResults.values as MutableList<DoctorsEntity>
                notifyDataSetChanged()
            }
        }
    }

}