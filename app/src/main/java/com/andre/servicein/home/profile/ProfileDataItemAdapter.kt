package com.andre.servicein.home.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andre.servicein.R


class ProfileDataItemAdapter(var list:ArrayList<ProfileDataIItemModel>):
    RecyclerView.Adapter<ProfileDataItemAdapter.ProfileDataItemViewHolder>() {
    inner class ProfileDataItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.layout_profile_picture)
        var name = view.findViewById<TextView>(R.id.layout_profile_name)
        var job = view.findViewById<TextView>(R.id.layout_profle_job)
        var city = view.findViewById<TextView>(R.id.layout_profile_distance)
        var rating = view.findViewById<TextView>(R.id.layout_profile_rating)

        fun bind(model: ProfileDataIItemModel){
            image?.setImageResource(model.image)
            name.text = model.name
            job.text = model.job
            city.text = model.distance
            rating.text = model.rating
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileDataItemViewHolder {
        return ProfileDataItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.profile_card, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ProfileDataItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}