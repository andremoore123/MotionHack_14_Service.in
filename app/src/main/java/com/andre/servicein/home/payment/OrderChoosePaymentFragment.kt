package com.andre.servicein.home.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.Navigation
import com.andre.servicein.R
import com.google.android.material.appbar.MaterialToolbar

class OrderChoosePaymentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_choose_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonBank = view.findViewById<ConstraintLayout>(R.id.payment_method_bank)
        val buttonGopay = view.findViewById<ConstraintLayout>(R.id.payment_method_gopay)
        val appbar: MaterialToolbar = view.findViewById(R.id.order_choose_tool_bar)

        buttonBank.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_orderChoosePayment_to_orderPaymentBank)
        }
        appbar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_orderChoosePayment_to_orderFormFragment)
        }
    }
}