package com.example.adminmunchmate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminmunchmate.databinding.ActivitySignupBinding
import com.example.adminmunchmate.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var nameofResturant: String
    private lateinit var database: DatabaseReference
    private val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase auth
        auth = FirebaseAuth.getInstance()

        // Initialize Firebase database
        database = Firebase.database.reference

        binding.alredyAccountButton.setOnClickListener {
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
        }

        binding.createuserButton.setOnClickListener {
            userName = binding.editName.text.toString().trim()
            email = binding.editEmailorPhone.text.toString().trim()
            password = binding.editPassword.text.toString().trim()
            nameofResturant = binding.editResturant.text.toString().trim()

            if (userName.isBlank() || nameofResturant.isBlank() || email.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Please Fill all the Details", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(email, password)
            }
        }
    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this,"Account is Created Succesfully",Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this,"Account Creation Failed",Toast.LENGTH_SHORT).show()
                Log.e("Account","Create Account Failed",task.exception)
            }
        }
    }

    private fun saveUserData() {
        userName = binding.editName.text.toString().trim()
        email = binding.editEmailorPhone.text.toString().trim()
        password = binding.editPassword.text.toString().trim()
        nameofResturant = binding.editResturant.text.toString().trim()
        val user = UserModel(userName, nameofResturant, email, password)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }


}
