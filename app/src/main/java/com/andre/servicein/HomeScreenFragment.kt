package com.andre.servicein

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.andre.servicein.boarding.OnBoardingAdapter
import com.andre.servicein.home.BannerItemAdapter
import com.andre.servicein.home.BannerItemModel
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class HomeScreenFragment : Fragment() {

    var bannerItemAdapter: BannerItemAdapter? = null
    var list: ArrayList<BannerItemModel>? = ArrayList()
    init {
        addList()
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
    }
    fun addList(){
        list?.add(BannerItemModel(R.drawable.banner_1))
        list?.add(BannerItemModel(R.drawable.banner_2))
        list?.add(BannerItemModel(R.drawable.banner_1))
        list?.add(BannerItemModel(R.drawable.banner_2))
    }
    private fun setUpBannerPager(){
        bannerItemAdapter = BannerItemAdapter(list!!)
        var viewPager = view!!.findViewById<ViewPager2>(R.id.home_banner_pager)
        viewPager?.adapter = bannerItemAdapter
        var dotIndicator = view?.findViewById<DotsIndicator>(R.id.home_banner_indicator)
        dotIndicator?.setViewPager2(viewPager)
    }
}