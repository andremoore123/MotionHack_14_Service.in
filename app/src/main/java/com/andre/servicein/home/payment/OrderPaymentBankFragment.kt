package com.andre.servicein.home.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.andre.servicein.R
import com.google.android.material.appbar.MaterialToolbar

class OrderPaymentBank : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_payment_bank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appbar: MaterialToolbar = view.findViewById(R.id.order_chosen_payment_tool_bar)
        appbar.setNavigationOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_orderPaymentBank_to_orderChoosePayment)
        }
    }
}