package com.example.adminmunchmate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.adminmunchmate.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
            email=binding.loginemail.text.toString().trim()
            password=binding.loginPassword.text.toString().trim()

            if(email.isBlank()||password.isBlank()){
                Toast.makeText(this,"Please Fill the Detail",Toast.LENGTH_SHORT).show()
            }else{
                createUserAccount(email,password)
            }


        }
        binding.donthaveAccountbutton.setOnClickListener {
            val i=Intent(this,SignupActivity::class.java)
            startActivity(i)
        }
    }

    private fun createUserAccount(email: String, password: String) {
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                val user:FirebaseUser?=auth.currentUser
                Toast.makeText(this,"Tu to apna bhai hai",Toast.LENGTH_SHORT).show()
                updateUi(user)
            }else{
               Toast.makeText(this,"Please Go to SignUp and Create Your Profile",Toast.LENGTH_SHORT).show()
            }
        }
    }



    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this,MainActivity::class.java))
    }
}