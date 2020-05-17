package com.ehomosepian.club.ui.base.multiadapter.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ehomosepian.club.R
import com.ehomosepian.club.ui.base.multiadapter.FeedItemViewBinder
import com.ehomosepian.club.ui.base.multiadapter.models.VeriticalImageInnerModel
import kotlinx.android.synthetic.main.adapter_inner_veritcal_image.view.*

class VerticalImagesInnerViewBinder(val block : (data: VeriticalImageInnerModel) -> Unit) : FeedItemViewBinder<VeriticalImageInnerModel, VerticalImagesInnerViewHolder>(
    VeriticalImageInnerModel::class.java) {

    override fun createViewHolder(parent: ViewGroup): VerticalImagesInnerViewHolder {
        return VerticalImagesInnerViewHolder(
            LayoutInflater.from(parent.context).inflate(getFeedItemType(), parent, false),block)
    }

    override fun bindViewHolder(model: VeriticalImageInnerModel, viewHolder: VerticalImagesInnerViewHolder) {
        viewHolder.bind(model)
    }

    override fun getFeedItemType() = R.layout.adapter_inner_veritcal_image

    override fun areContentsTheSame(oldItem: VeriticalImageInnerModel, newItem: VeriticalImageInnerModel) = oldItem == newItem

    override fun areItemsTheSame(oldItem: VeriticalImageInnerModel, newItem: VeriticalImageInnerModel) : Boolean {
        return oldItem.Image == newItem.Image
    }
}


class VerticalImagesInnerViewHolder(val view : View, val block : (data: VeriticalImageInnerModel) -> Unit)
    : RecyclerView.ViewHolder(view) {

    fun bind(data: VeriticalImageInnerModel) {

        itemView.setOnClickListener {
            block(data)
        }

        itemView.apply {
            Glide
                .with(itemView.context)
                .load(data.Image)
                .into(im_vertical_inner)
        }
    }
}