package com.andre.servicein.home.activity

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.andre.servicein.R
import com.andre.servicein.home.chat.ChatDataItemModel


class ActivityDataItemAdapter(var list:ArrayList<ActivityDataItemModel>):
    RecyclerView.Adapter<ActivityDataItemAdapter.ActivityDataItemViewHolder>() {
    inner class ActivityDataItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.layout_activity_name)
        var job = view.findViewById<TextView>(R.id.layout_activity_job)
        var status = view.findViewById<TextView>(R.id.layout_activity_status)
        val constraintView = view.findViewById<ConstraintLayout>(R.id.layout_activity_constraint)

        fun bind(model: ActivityDataItemModel){
            name.text = model.name
            job.text = model.job
            status.text = model.status
            if (status.text.toString() != "On going"){
                constraintView.setBackgroundColor(Color.parseColor("#999999"))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityDataItemViewHolder {
        return ActivityDataItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_layout, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ActivityDataItemViewHolder, position: Int) {
        holder.bind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

}