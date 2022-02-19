package com.andre.servicein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        //Declare all of the input and button
        var inputEmail = findViewById<TextInputEditText>(R.id.register_input_email)
        val emailLayout = findViewById<TextInputLayout>(R.id.register_layout_email)
        val passwordLayout = findViewById<TextInputLayout>(R.id.register_layout_password)
        var inputPassword = findViewById<TextInputEditText>(R.id.register_input_password)
        var buttonLogin = findViewById<Button>(R.id.button_login)
        var buttonRegister = findViewById<Button>(R.id.button_register)

        //Handling Register Button
        buttonRegister.setOnClickListener{
            var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener{
            if (inputEmail.text.toString().isEmpty()){
                emailLayout.error = getString(R.string.errorEmpty)
                inputEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.text.toString()).matches()){
                emailLayout.error = "Email Tidak Valid"
                inputEmail.requestFocus()
                return@setOnClickListener
            }
            if (inputPassword.text.toString().isEmpty() || inputPassword.text.toString().trim().length < 8){
                passwordLayout.error = "Password minimal 8 karakter"
                inputPassword.requestFocus()
                return@setOnClickListener
            }
            loginUser(inputEmail.text.toString(), inputPassword.text.toString())
            

        }

    }

    private fun loginUser(emai: String, password: String) {
        auth.signInWithEmailAndPassword(emai, password)
            .addOnCompleteListener(this){
                if (it.isSuccessful){
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}