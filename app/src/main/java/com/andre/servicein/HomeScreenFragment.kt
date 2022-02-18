package com.andre.servicein

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.andre.servicein.home.BannerItemAdapter
import com.andre.servicein.home.BannerItemModel
import com.andre.servicein.home.profile.ProfileDataIItemModel
import com.andre.servicein.home.profile.ProfileDataItemAdapter
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeScreenFragment : Fragment() {

    var bannerItemAdapter: BannerItemAdapter? = null
    var listBanner: ArrayList<BannerItemModel>? = ArrayList()
    var listTopRecommendationAdapter: ProfileDataItemAdapter? = null
    var listTopRecommendation: ArrayList<ProfileDataIItemModel> = ArrayList()
    lateinit var recyclerViewListTopRecommendation: RecyclerView

    init {
        addListBanner()
        addlistTopRecommendation()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpBannerPager()
        setUpTopListRecommendation()
    }
    private fun addListBanner(){
        listBanner?.add(BannerItemModel(R.drawable.banner_1))
        listBanner?.add(BannerItemModel(R.drawable.banner_2))
        listBanner?.add(BannerItemModel(R.drawable.banner_1))
        listBanner?.add(BannerItemModel(R.drawable.banner_2))
    }
    private fun addlistTopRecommendation(){
        listTopRecommendation?.add(ProfileDataIItemModel(
            R.drawable.profile_pict1,
            "Anton Sadikin",
            "Ahli Servis AC",
            "Jakarta",
            "4,5"
        ))
        listTopRecommendation?.add(ProfileDataIItemModel(
            R.drawable.profile_pict1,
            "Anton Sadikin",
            "Ahli Servis AC",
            "Jakarta",
            "4,5"
        ))
        listTopRecommendation?.add(ProfileDataIItemModel(
            R.drawable.profile_pict1,
            "Anton Sadikin",
            "Ahli Servis AC",
            "Jakarta",
            "4,5"
        ))
        listTopRecommendation?.add(ProfileDataIItemModel(
            R.drawable.profile_pict1,
            "Anton Sadikin",
            "Ahli Servis AC",
            "Jakarta",
            "4,5"
        ))
        listTopRecommendation?.add(ProfileDataIItemModel(
            R.drawable.profile_pict1,
            "Anton Sadikin",
            "Ahli Servis AC",
            "Jakarta",
            "4,5"
        ))
    }
    private fun setUpBannerPager(){
        bannerItemAdapter = BannerItemAdapter(listBanner!!)
        var viewPager = view!!.findViewById<ViewPager2>(R.id.home_banner_pager)
        viewPager?.adapter = bannerItemAdapter
        var dotIndicator = view?.findViewById<DotsIndicator>(R.id.home_banner_indicator)
        dotIndicator?.setViewPager2(viewPager)
    }
    private fun setUpTopListRecommendation(){
        recyclerViewListTopRecommendation = view!!.findViewById(R.id.home_recycler_recommendation)
        recyclerViewListTopRecommendation.layoutManager = LinearLayoutManager(this.requireContext())
        recyclerViewListTopRecommendation.adapter = listTopRecommendationAdapter
        recyclerViewListTopRecommendation.adapter = ProfileDataItemAdapter(listTopRecommendation)
    }
}