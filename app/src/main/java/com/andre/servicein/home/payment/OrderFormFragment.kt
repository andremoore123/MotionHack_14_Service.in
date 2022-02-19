package com.andre.servicein.home.payment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.EditText
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import com.andre.servicein.R
import com.andre.servicein.RegisterActivity
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import java.util.*

class OrderFormFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_form, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectDate = view.findViewById<EditText>(R.id.order_form_input_date)
        val appbar: MaterialToolbar = view.findViewById(R.id.order_form_tool_bar)
        val butonPayment: ConstraintLayout = view.findViewById(R.id.order_form_button_payment)
        selectDate.setOnClickListener {
            val datePickerDialog = RegisterActivity.DatePickerFragment { year, month, day ->
                selectDate.setText("$month/$day/$year")
            }
            datePickerDialog.show((activity as AppCompatActivity).supportFragmentManager, "datePicker")
        }
        butonPayment.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_orderFormFragment_to_orderChoosePayment)
        }

    }

    // Class To Handle Date Picker Fragment
    class DatePickerFragment(
        private val setDate: (
            year: Int, month: Int,
            day: Int
        ) -> Unit
    ) : DialogFragment(),
        DatePickerDialog.OnDateSetListener {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            // Use the current date as the default date in the picker
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // Create a new instance of DatePickerDialog and return it
            return DatePickerDialog(activity!!, this, year, month, day)
        }

        override fun onDateSet(p0: DatePicker?, year: Int, month: Int, date: Int) {
            setDate(year, month, date)
        }
    }
}

