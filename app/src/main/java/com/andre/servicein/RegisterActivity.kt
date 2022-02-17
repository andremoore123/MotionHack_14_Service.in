package com.andre.servicein

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputLayout
import java.util.*


class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Declare all of the input and button
        val appbar: MaterialToolbar = findViewById(R.id.top_bar_register)
        val selectDate = findViewById<EditText>(R.id.register_input_date)
        val dateLayout = findViewById<TextInputLayout>(R.id.layout_date)
        val inputName = findViewById<EditText>(R.id.register_input_nama)
        val nameLayout = findViewById<TextInputLayout>(R.id.layout_nama)
        val inputEmail = findViewById<EditText>(R.id.register_input_email)
        val emailLayout = findViewById<TextInputLayout>(R.id.layout_email)
        val inputPassword = findViewById<EditText>(R.id.register_input_password)
        val passwordLayout = findViewById<TextInputLayout>(R.id.layout_password)
        val inputPhone = findViewById<EditText>(R.id.register_input_phone)
        val phoneLayout = findViewById<TextInputLayout>(R.id.layout_phone)
        val buttonRegister = findViewById<Button>(R.id.button_register2)
        val checkBox = findViewById<CheckBox>(R.id.register_check_box)

        // Set App Bar Navigation back to Login Activity
        appbar.setNavigationOnClickListener { onBackPressed() }

        // Set Picker Calendar
        selectDate.setOnClickListener {
            val datePickerDialog = DatePickerFragment{
                year, month, day -> selectDate.setText("$month/$day/$year")
            }
            datePickerDialog.show(supportFragmentManager,"datePicker")
        }

        // Click Register Button
        buttonRegister.setOnClickListener{
            val name = if (inputName.text.isNotEmpty()) inputName.text else nameLayout.error = getString(R.string.errorEmpty)
            val email = if (inputEmail.text.isNotEmpty()) inputName.text else emailLayout.error = getString(R.string.errorEmpty)
            val phone = if (inputPhone.text.isNotEmpty()) inputPhone.text else phoneLayout.error = getString(R.string.errorEmpty)
            val date = if (selectDate.text.isNotEmpty()) selectDate.text else dateLayout.error = getString(R.string.errorEmpty)
            val password = if (inputPassword.text.isNotEmpty()) inputPassword.text else passwordLayout.error = getString(R.string.errorEmpty)

            val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    class DatePickerFragment(private val setDate: (year: Int, month: Int,
                                                   day: Int)->Unit) : DialogFragment(),
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