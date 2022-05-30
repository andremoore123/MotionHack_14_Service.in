package com.andre.servicein.home.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andre.servicein.R
import com.andre.servicein.home.chat.ChatDataItemModel
import com.andre.servicein.home.chat.ChatItemDataAdapter

class ActivityScreeenFragment : Fragment() {

    var listActivity: ArrayList<ActivityDataItemModel> = ArrayList()
    var activityDataItemAdapter: ActivityDataItemAdapter? = null
    lateinit var recyclerActivity: RecyclerView

    init {
        addlistActivity()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity_screeen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerViewActivity()
    }

    private fun addlistActivity(){
        listActivity.add(ActivityDataItemModel(
            "Tony Sumanto",
            "Ahli Servis AC",
            "On going"
        ))
        listActivity.add(ActivityDataItemModel(
            "Peter Parker",
            "Ahli Penangkap Laba-laba",
            "Tue"
        ))
        listActivity.add(ActivityDataItemModel(
            "Jhon Ma",
            "Ahli Servis TV",
            "Sat"
        ))
        listActivity.add(ActivityDataItemModel(

            "Tony Sumanto",
            "Ahli Servis Laptop",
            "Fri"
        ))
        listActivity.add(ActivityDataItemModel(
            "Tony Sumanto",
            "Ahli Servis Kipas",
            "Mon"
        ))
    }
    private fun setUpRecyclerViewActivity(){
        recyclerActivity = view!!.findViewById(R.id.actvity_recycler_actvity)
        recyclerActivity.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerActivity.adapter = activityDataItemAdapter
        recyclerActivity.adapter = ActivityDataItemAdapter(listActivity)
    }
}