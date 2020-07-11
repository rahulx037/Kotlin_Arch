package dragor.international.ui.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import dragor.international.R
import dragor.international.api.models.ProfileItem
import dragor.international.databinding.ProfileListItemBinding
import dragor.international.ui.base.BaseAdapter
import java.lang.String
import java.util.*

class ProfileRecyclerAdapter(@NonNull private val clickListner: ProfileItemListner ) : BaseAdapter<ProfileRecyclerAdapter.ItemsViewHolder,ProfileItem>(),
    Filterable {
    var mItemsEntities = mutableListOf<ProfileItem>()
    var mItemsEntitiesFiltered = mutableListOf<ProfileItem>()

    private var layoutInflater: LayoutInflater? = null

    private var mMenuItemsListCallback: ProfileItemListner? = null
    private lateinit var binding: ProfileListItemBinding

    init {
        mItemsEntities = ArrayList()
        mItemsEntitiesFiltered = ArrayList()
        mMenuItemsListCallback=clickListner
    }



    override fun setData(data: List<ProfileItem>) {
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
            R.layout.profile_list_item,
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
            val itemsbinding: ProfileListItemBinding,
            callback: ProfileItemListner?
    ) :
        RecyclerView.ViewHolder(itemsbinding.root) {
        fun onBind(itementity: ProfileItem?) {
            itemsbinding.category = itementity
            itemsbinding.executePendingBindings()
        }

        init {
            itemsbinding.root.setOnClickListener { view -> callback?.onListItemClick(itemsbinding.category) }
        }
    }

    override fun getFilter(): Filter? {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                mItemsEntitiesFiltered = if (charString.isEmpty()) {
                    mItemsEntities
                } else {
                    val filteredList: MutableList<ProfileItem> = ArrayList<ProfileItem>()
                    for (row in mItemsEntities) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (String.valueOf(row.title) == charString || row.sub_title
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
                mItemsEntitiesFiltered = filterResults.values as MutableList<ProfileItem>
                notifyDataSetChanged()
            }
        }
    }

}