package com.andre.servicein

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Layout
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
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
        val modalBottomSheet = ModalBottomSheet()

        // Set App Bar Navigation back to Login Activity
        appbar.setNavigationOnClickListener { onBackPressed() }

        // Set Picker Calendar
        selectDate.setOnClickListener {
            val datePickerDialog = DatePickerFragment { year, month, day ->
                selectDate.setText("$month/$day/$year")
            }
            datePickerDialog.show(supportFragmentManager, "datePicker")
        }


        // Click Register Button
        buttonRegister.setOnClickListener {
            var noError = false
            val name = if (inputName.text.isNotEmpty()) {
                inputName.text
                noError = true
            } else {
                nameLayout.error = getString(R.string.errorEmpty)
                noError = false
            }
            val email = if (inputEmail.text.isNotEmpty()) {
                inputName.text
                noError = true
            } else {
                emailLayout.error = getString(R.string.errorEmpty)
                noError = false
            }
            val phone = if (inputPhone.text.isNotEmpty()) {
                noError = true
                inputPhone.text
            } else {
                phoneLayout.error = getString(R.string.errorEmpty)
                noError = false
            }
            val date = if (selectDate.text.isNotEmpty()) {
                noError = true
                selectDate.text
            } else {
                dateLayout.error = getString(R.string.errorEmpty)
                noError = false
            }
            val password = if (inputPassword.text.isNotEmpty()) {
                noError = true
                inputPassword.text
            } else {
                passwordLayout.error = getString(R.string.errorEmpty)
                noError = false
            }

            // To Check no Empty Input
            if (noError) {
                modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
            }
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

    class ModalBottomSheet() : BottomSheetDialogFragment() {

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? = inflater.inflate(R.layout.bottom_sheet_otp, container, false)

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//            Set Bottom Sheet
                val editText1 = view.findViewById<EditText>(R.id.editText1)
                val editText2= view.findViewById<EditText>(R.id.editText2)
                val editText3 = view.findViewById<EditText>(R.id.editText3)
                val editText4 = view.findViewById<EditText>(R.id.editText4)
                //GenericTextWatcher here works only for moving to next EditText when a number is entered
                //first parameter is the current EditText and second parameter is next EditText
                    editText1.addTextChangedListener(GenericTextWatcher(editText1, editText2))
                    editText2.addTextChangedListener(GenericTextWatcher(editText2, editText3))
                    editText3.addTextChangedListener(GenericTextWatcher(editText3, editText4))
                    editText4.addTextChangedListener(GenericTextWatcher(editText4, null))

                //GenericKeyEvent here works for deleting the element and to switch back to previous EditText
                //first parameter is the current EditText and second parameter is previous EditText
                        editText1.setOnKeyListener(GenericKeyEvent(editText1, null))
                        editText2.setOnKeyListener(GenericKeyEvent(editText2, editText1))
                        editText3.setOnKeyListener(GenericKeyEvent(editText3, editText2))
                        editText4.setOnKeyListener(GenericKeyEvent(editText4,editText3))

            val otpYaButton = view.findViewById<Button>(R.id.otp_button_iya)
            otpYaButton.setOnClickListener {
                    val intent = Intent(this@ModalBottomSheet.requireContext(), HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
        }
        companion object {
            const val TAG = "ModalBottomSheet"
        }


        class GenericKeyEvent internal constructor(
            private val currentView: EditText,
            private val previousView: EditText?
        ) : View.OnKeyListener {
            override fun onKey(p0: View?, keyCode: Int, event: KeyEvent?): Boolean {
                if (event!!.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.id != R.id.editText1 && currentView.text.isEmpty()) {
                    //If current is empty then previous EditText's number will also be deleted
                    previousView!!.text = null
                    previousView.requestFocus()
                    return true
                }
                return false
            }


        }

        class GenericTextWatcher internal constructor(
            private val currentView: View,
            private val nextView: View?
        ) :
            TextWatcher {
            override fun afterTextChanged(editable: Editable) { // TODO Auto-generated method stub
                val text = editable.toString()
                when (currentView.id) {
                    R.id.editText1 -> if (text.length == 1) nextView!!.requestFocus()
                    R.id.editText2 -> if (text.length == 1) nextView!!.requestFocus()
                    R.id.editText3 -> if (text.length == 1) nextView!!.requestFocus()
                    //You can use EditText4 same as above to hide the keyboard
                }
            }

            override fun beforeTextChanged(
                arg0: CharSequence,
                arg1: Int,
                arg2: Int,
                arg3: Int
            ) { // TODO Auto-generated method stub
            }

            override fun onTextChanged(
                arg0: CharSequence,
                arg1: Int,
                arg2: Int,
                arg3: Int
            ) { // TODO Auto-generated method stub
            }

        }
    }
}