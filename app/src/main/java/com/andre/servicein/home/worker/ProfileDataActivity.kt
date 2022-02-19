package com.andre.servicein.home.worker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import com.andre.servicein.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileDataActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_data)
        val buttonFirst = findViewById<ConstraintLayout>(R.id.buttom_sheet_button_pay)
        val buttonNext = findViewById<ConstraintLayout>(R.id.buttom_sheet_button_next_pay)
        val chat = findViewById<ConstraintLayout>(R.id.buttom_sheet_button_chat)
        val groupFirstClick = findViewById<Group>(R.id.button_sheet_group_pay)
        val containerDeskripsi = findViewById<ConstraintLayout>(R.id.bottom_sheet_container_deskripsi)
        val containerChoose = findViewById<ConstraintLayout>(R.id.bottom_sheet_container_pay)
        val backButton = findViewById<FloatingActionButton>(R.id.button_sheet_button_back)

        buttonFirst.setOnClickListener{
            groupFirstClick.visibility = View.GONE
            containerDeskripsi.visibility = View.GONE
            containerChoose.visibility = View.VISIBLE
        }
        backButton.setOnClickListener{ onBackPressed() }
    }
}
