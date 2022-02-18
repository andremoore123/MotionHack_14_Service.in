package com.andre.servicein.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andre.servicein.R


class BannerItemAdapter(var list:ArrayList<BannerItemModel>):
    RecyclerView.Adapter<BannerItemAdapter.BannerItemViewHolder>() {
    inner class BannerItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.banner_image)

        fun bind(model: BannerItemModel){
            image?.setImageResource(model.Image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerItemViewHolder {
        return BannerItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.banner_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BannerItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}