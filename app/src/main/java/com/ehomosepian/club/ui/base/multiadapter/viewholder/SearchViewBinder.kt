package com.ehomosepian.club.ui.base.multiadapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ehomosepian.club.R
import com.ehomosepian.club.ui.base.multiadapter.FeedItemViewBinder
import com.ehomosepian.club.ui.base.multiadapter.models.SearchModel
import kotlinx.android.synthetic.main.adapter_vertical_image.view.*

class SearchViewBinder(val block : (data: SearchModel) -> Unit) : FeedItemViewBinder<SearchModel, SearchViewHolder>(
    SearchModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false),block)
    }

    override fun bindViewHolder(model: SearchModel, viewHolder: SearchViewHolder) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.view_search_viewholder

    override fun areContentsTheSame(oldItem: SearchModel, newItem: SearchModel) = oldItem == newItem
    override fun areItemsTheSame(oldItem: SearchModel, newItem: SearchModel): Boolean {
        return oldItem.text == newItem.text
    }


}


class SearchViewHolder(val view : View, val block : (data: SearchModel) -> Unit)
    : RecyclerView.ViewHolder(view) {

    fun bind(data: SearchModel) {

        itemView.setOnClickListener {
            block(data)
        }

       /* itemView.apply {
            Glide
                .with(itemView.context)
                .load(data.Image)
                .into(im_vertical)
        }*/
    }
}