package com.andre.servicein.home.worker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.Group
import com.andre.servicein.R
import com.andre.servicein.home.payment.PaymentOrder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

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
        val addButton = findViewById<FloatingActionButton>(R.id.bottom_sheet_button_add)
        val minusButton = findViewById<FloatingActionButton>(R.id.bottom_sheet_button_minus)
        val totalText =  findViewById<TextView>(R.id.bottom_sheet_total)

        buttonFirst.setOnClickListener{
            groupFirstClick.visibility = View.GONE
            containerDeskripsi.visibility = View.GONE
            containerChoose.visibility = View.VISIBLE
        }
        backButton.setOnClickListener{ onBackPressed() }

        buttonNext.setOnClickListener{
            intent = Intent(this@ProfileDataActivity, PaymentOrder::class.java)
            startActivity(intent)
        }
//        addButton.setOnClickListener {
//            var total = totalText.toString().toInt()
//            total += 1
//            totalText.text = total.toString()
//        }

//        minusButton.setOnClickListener {
//            var total = totalText.toString().toInt()
//            total += 1
//            totalText.text = total.toString()
//        }
    }
}
