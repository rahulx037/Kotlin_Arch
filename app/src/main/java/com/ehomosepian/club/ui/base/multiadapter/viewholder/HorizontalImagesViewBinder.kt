package com.ehomosepian.club.ui.base.multiadapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ehomosepian.club.R
import com.ehomosepian.club.ui.base.multiadapter.FeedItemViewBinder
import com.ehomosepian.club.ui.base.multiadapter.models.HorizontalImageModel
import kotlinx.android.synthetic.main.adapter_horizontal_image.view.*

class HorizontalImagesViewBinder(val block : (data: HorizontalImageModel) -> Unit) : FeedItemViewBinder<HorizontalImageModel, HorizontalImagesViewHolder>(
    HorizontalImageModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): HorizontalImagesViewHolder {
        return HorizontalImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false),block)
    }

    override fun bindViewHolder(model: HorizontalImageModel, viewHolder: HorizontalImagesViewHolder) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.adapter_horizontal_image

    override fun areContentsTheSame(oldItem: HorizontalImageModel, newItem: HorizontalImageModel) = oldItem == newItem

    override fun areItemsTheSame(oldItem: HorizontalImageModel, newItem: HorizontalImageModel) : Boolean {
        return oldItem.Image == newItem.Image
    }
}


class HorizontalImagesViewHolder(val view : View, val block : (data: HorizontalImageModel) -> Unit)
    : RecyclerView.ViewHolder(view) {

    fun bind(data: HorizontalImageModel) {

        itemView.setOnClickListener {
            block(data)
        }

        itemView.apply {
            Glide
                .with(itemView.context)
                .load(data.Image)
                .into(im_horizontal)
        }
    }
}