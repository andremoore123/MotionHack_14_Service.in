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
import android.util.Patterns
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.HashMap


class RegisterActivity : AppCompatActivity() {
    lateinit var name: String
    lateinit var email : String
    lateinit var phone : String
    lateinit var date : String
    lateinit var password : String
    private lateinit var auth: FirebaseAuth
    val modalBottomSheet = ModalBottomSheet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()
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
            val datePickerDialog = DatePickerFragment { year, month, day ->
                selectDate.setText("$month/$day/$year")
            }
            datePickerDialog.show(supportFragmentManager, "datePicker")
        }


        // Click Register Button
        buttonRegister.setOnClickListener {
             name = if (inputName.text.isEmpty()){
                nameLayout.error = getString(R.string.errorEmpty)
                inputEmail.requestFocus()
                return@setOnClickListener
            } else {
                inputName.text.toString()
            }
            email = if (inputEmail.text.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()) {
                emailLayout.error = getString(R.string.errorEmpty)
                inputEmail.requestFocus()
                return@setOnClickListener
            } else {
                inputEmail.text.toString()
            }
            phone = if (inputPhone.text.isEmpty()) {
                phoneLayout.error = getString(R.string.errorEmpty)
                inputPhone.requestFocus()
                return@setOnClickListener
            } else {
                inputPhone.text.toString()
            }
            date = if (selectDate.text.isEmpty()) {
                dateLayout.error = getString(R.string.errorEmpty)
                selectDate.requestFocus()
                return@setOnClickListener
            } else {
                selectDate.text.toString()
            }
            password = if (inputPassword.text.isEmpty()){
                passwordLayout.error = getString(R.string.errorEmpty)
                selectDate.requestFocus()
                return@setOnClickListener
            } else {
                inputPassword.text.toString()
            }
            registerUserWithEmailAndPassword(email, password, phone, date)
        }
    }
    private fun registerUserWithEmailAndPassword(email: String, password: String, phone: String,
    bornDate: String){
       auth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener{
               if (it.isSuccessful){
                   saveFireStore(email, password, phone, bornDate)
                   modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
               } else {
                   Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
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
    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(this@RegisterActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
    fun saveFireStore(name: String, email: String, phone: String, bornDate: String){
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["name"] = name
        user["email"] = email
        user["phone"] = phone
        user["bornDate"] = bornDate

        db.collection("users")
            .add(user)
            .addOnFailureListener{
                Toast.makeText(this@RegisterActivity, "Register Failed", Toast.LENGTH_SHORT).show()

            }
    }
}