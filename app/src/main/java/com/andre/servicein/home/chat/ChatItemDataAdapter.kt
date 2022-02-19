package com.andre.servicein.home.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andre.servicein.R


class ChatItemDataAdapter(var list:ArrayList<ChatDataItemModel>):
    RecyclerView.Adapter<ChatItemDataAdapter.ChatItemDataViewHolder>() {
    inner class ChatItemDataViewHolder(view: View): RecyclerView.ViewHolder(view){
        var picture = view.findViewById<ImageView>(R.id.layout_chat_profile_pict)
        var name = view.findViewById<TextView>(R.id.layout_chat_profile_name)
        var job = view.findViewById<TextView>(R.id.layout_chat_profile_job)
        var lastChat = view.findViewById<TextView>(R.id.layout_chat_last_chat)

        fun bind(model: ChatDataItemModel){
            picture?.setImageResource(model.picture)
            name.text = model.name
            job.text = model.job
            lastChat.text = model.lastChat
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemDataViewHolder {
        return ChatItemDataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.chat_item_layout, parent, false)
        )
    }
    override fun onBindViewHolder(holder: ChatItemDataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}