package com.ehomosepian.club.ui.base.multiadapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ehomosepian.club.R
import com.ehomosepian.club.ui.base.multiadapter.FeedAdapter
import com.ehomosepian.club.ui.base.multiadapter.FeedItemBinder
import com.ehomosepian.club.ui.base.multiadapter.FeedItemClass
import com.ehomosepian.club.ui.base.multiadapter.FeedItemViewBinder
import com.ehomosepian.club.ui.base.multiadapter.models.VeriticalImageInnerModel
import com.ehomosepian.club.ui.base.multiadapter.models.VerticalImageListModel
import kotlinx.android.synthetic.main.adapter_recycleriew.view.*
import kotlinx.android.synthetic.main.adapter_recyclerview_vertical.view.*

class VerticalImagesListViewBinder(val block : (data: VeriticalImageInnerModel) -> Unit)
    : FeedItemViewBinder<VerticalImageListModel, VerticalImagesListViewHolder>(
    VerticalImageListModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): VerticalImagesListViewHolder {
        return VerticalImagesListViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false),block)
    }

    override fun bindViewHolder(model: VerticalImageListModel, viewHolder: VerticalImagesListViewHolder) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.adapter_recyclerview_vertical

    override fun areContentsTheSame(oldItem: VerticalImageListModel, newItem: VerticalImageListModel) = oldItem.Images == newItem.Images

    override fun areItemsTheSame(oldItem: VerticalImageListModel, newItem: VerticalImageListModel) : Boolean {
        return oldItem.id == newItem.id
    }
}


class VerticalImagesListViewHolder(val view : View, val block : (data: VeriticalImageInnerModel) -> Unit)
    : RecyclerView.ViewHolder(view) {

    fun bind(data: VerticalImageListModel) {

        var adapter : FeedAdapter? = null

        itemView.setOnClickListener {

        }

        itemView.apply {
            val verticalImagesViewBinder = VerticalImagesInnerViewBinder { horizontalImageModel : VeriticalImageInnerModel ->
                block(horizontalImageModel)}
            val viewBinders = mutableMapOf<FeedItemClass, FeedItemBinder>()
            @Suppress("UNCHECKED_CAST")
            viewBinders.put(
                verticalImagesViewBinder.modelClass,
                verticalImagesViewBinder as FeedItemBinder)
            adapter = FeedAdapter(viewBinders)
            tv_horizontal_header_v?.text = data.title
            adapter_recycllerview_v?.apply {

                layoutManager = LinearLayoutManager(adapter_recycllerview_v?.context,
                    LinearLayoutManager.HORIZONTAL,false)

                ViewCompat.setNestedScrollingEnabled(adapter_recycllerview_v,true);
                adapter_recycllerview_v.setRecycledViewPool(recycledViewPool);

                if (adapter_recycllerview_v?.adapter == null) {
                    adapter_recycllerview_v?.adapter = adapter
                }
                (adapter_recycllerview_v?.adapter as FeedAdapter).submitList(
                    data.Images as List<Any>? ?: emptyList())
            }
        }
    }
}