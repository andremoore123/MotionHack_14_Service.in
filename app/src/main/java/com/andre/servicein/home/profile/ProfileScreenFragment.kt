package com.andre.servicein.home.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.andre.servicein.HomeActivity
import com.andre.servicein.LoginActivity
import com.andre.servicein.R
import com.google.firebase.auth.FirebaseAuth

class ProfileScreenFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.profile_button_logout).setOnClickListener{
            auth.signOut()
        val intent = Intent(this@ProfileScreenFragment.requireContext(), LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        }
    }
}