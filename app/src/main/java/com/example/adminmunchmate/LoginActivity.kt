package com.example.adminmunchmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminmunchmate.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val binding:ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initilise firbase auth
        auth=Firebase.auth
        //initilise Firebase Auth
        database=Firebase.database.reference
        binding.LoginButton.setOnClickListener {
            val i= Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        binding.donthaveAccountbutton.setOnClickListener {
            val i=Intent(this,SignupActivity::class.java)
            startActivity(i)
        }
    }
}