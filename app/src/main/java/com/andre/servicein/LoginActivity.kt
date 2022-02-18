package com.andre.servicein

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Declare all of the input and button
        var inputEmail = findViewById<TextInputEditText>(R.id.register_input_email)
        var inputPassword = findViewById<TextInputEditText>(R.id.register_input_password)
        var buttonLogin = findViewById<Button>(R.id.button_login)
        var buttonRegister = findViewById<Button>(R.id.button_register)

        //Handling Register Button
        buttonRegister.setOnClickListener{
            var intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener{
            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }

    }
}