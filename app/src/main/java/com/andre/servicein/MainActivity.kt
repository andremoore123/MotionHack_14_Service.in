package com.andre.servicein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.andre.servicein.boarding.OnBoardingAdapter
import com.andre.servicein.boarding.OnBoardingItemModel
import com.google.firebase.auth.FirebaseAuth
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class MainActivity : AppCompatActivity() {
    var adapterBoarding:OnBoardingAdapter? = null
    var list: ArrayList<OnBoardingItemModel>? = ArrayList()
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addList()
        auth = FirebaseAuth.getInstance()
        viewPagerSetUp()
    }

    private fun addList(){
        list?.add(
            OnBoardingItemModel(
                R.drawable.first_slide,
                getString(R.string.first_slide_title),
                getString(R.string.first_slide_text)
            )
        )
        list?.add(
            OnBoardingItemModel(
                R.drawable.second_slide,
                getString(R.string.second_slide_title),
                getString(R.string.second_slide_text)
            )
        )
        list?.add(
            OnBoardingItemModel(
                R.drawable.third_slide,
                getString(R.string.third_slide_title),
                getString(R.string.third_slide_text)
            )
        )
    }

    private fun viewPagerSetUp(){
        adapterBoarding = OnBoardingAdapter(list!!)
        var viewPager = findViewById<ViewPager2>(R.id.view_pager_boarding)
        viewPager.adapter = adapterBoarding
        var dotIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        dotIndicator.setViewPager2(viewPager)
        var nextButton = findViewById<TextView>(R.id.next_text)
        var skipButton = findViewById<TextView>(R.id.skip_text)
        skipButton.setOnClickListener{
            if (viewPager.currentItem+1 < adapterBoarding!!.itemCount){
                viewPager.currentItem = adapterBoarding!!.itemCount-1
            }
        }
        nextButton.setOnClickListener{
            if (viewPager.currentItem+1 < adapterBoarding!!.itemCount){
                viewPager.currentItem += 1
            } else {
                var intent = Intent(this@MainActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}