package com.andre.servicein.home.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andre.servicein.R

class ChatScreenFragment : Fragment() {
    var listChat: ArrayList<ChatDataItemModel> = ArrayList()
    var chatItemDataAdapter: ChatItemDataAdapter? = null
    lateinit var recyclerViewChatItem: RecyclerView

    init{
        addChatItem()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewChatItem()
    }

    private fun addChatItem(){
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict1,
            "Tony Sumanto",
            "Ahli Servis AC",
            "baik ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict2,
            "Tony Wicaksono",
            "Ahli Servis HP",
            "baik kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict3,
            "Tony Sumanto",
            "Ahli Servis Sepeda Motor",
            "siap ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict1,
            "Tony Sumanto",
            "Ahli Servis AC",
            "baik ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict2,
            "Tony Wicaksono",
            "Ahli Servis HP",
            "baik kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict3,
            "Tony Sumanto",
            "Ahli Servis Sepeda Motor",
            "siap ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict1,
            "Tony Sumanto",
            "Ahli Servis AC",
            "baik ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict2,
            "Tony Wicaksono",
            "Ahli Servis HP",
            "baik kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict3,
            "Tony Sumanto",
            "Ahli Servis Sepeda Motor",
            "siap ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict1,
            "Tony Sumanto",
            "Ahli Servis AC",
            "baik ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict2,
            "Tony Wicaksono",
            "Ahli Servis HP",
            "baik kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict3,
            "Tony Sumanto",
            "Ahli Servis Sepeda Motor",
            "siap ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict1,
            "Tony Sumanto",
            "Ahli Servis AC",
            "baik ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict2,
            "Tony Wicaksono",
            "Ahli Servis HP",
            "baik kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict3,
            "Tony Sumanto",
            "Ahli Servis Sepeda Motor",
            "siap ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict1,
            "Tony Sumanto",
            "Ahli Servis AC",
            "baik ditunggu kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict2,
            "Tony Wicaksono",
            "Ahli Servis HP",
            "baik kak!"
        ))
        listChat?.add(ChatDataItemModel(
            R.drawable.profile_pict3,
            "Tony Sumanto",
            "Ahli Servis Sepeda Motor",
            "siap ditunggu kak!"
        ))
    }
    private fun setUpRecyclerViewChatItem(){
        recyclerViewChatItem = view!!.findViewById(R.id.chat_recycler_item_chat)
        recyclerViewChatItem.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerViewChatItem.adapter = chatItemDataAdapter
        recyclerViewChatItem.adapter = ChatItemDataAdapter(listChat)
    }
}