package com.example.adminmunchmate

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.adminmunchmate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private  val binding:ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.addMenu.setOnClickListener{
            val i= Intent(this,AddItemActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.cardView6.setOnClickListener {
            val i=Intent(this,AllItemActivity::class.java)
            startActivity(i)
            finish()
        }
        binding.outForDeliveyButton.setOnClickListener{
            val i=Intent(this,OutForDeliveryActivity::class.java)
            startActivity(i)
        }
        binding.profile.setOnClickListener {
            val i=Intent(this,AdminProfileActivity::class.java)
            startActivity(i)
        }
        binding.createnewuser.setOnClickListener {
            val i=Intent(this,CreateUserActivity::class.java)
            startActivity(i)
        }
        binding.pendingOrderTextView.setOnClickListener {
            val i=Intent(this,PendingOrderActivity::class.java)
            startActivity(i)

        }
    }
}