package com.andre.servicein.boarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andre.servicein.R

class OnBoardingAdapter(var list:ArrayList<OnBoardingItemModel>):
    RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder>() {
    inner class OnBoardingViewHolder(view: View): RecyclerView.ViewHolder(view){
        var image = view.findViewById<ImageView>(R.id.item_image)
        var title = view.findViewById<TextView>(R.id.item_title)
        var text = view.findViewById<TextView>(R.id.item_text)

        fun bind(model: OnBoardingItemModel){
            image?.setImageResource(model.image)
            title?.text = model.title.toString()
            text?.text= model.text.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return OnBoardingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.boarding_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}