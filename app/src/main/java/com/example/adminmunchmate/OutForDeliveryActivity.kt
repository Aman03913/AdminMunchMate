package com.example.adminmunchmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminmunchmate.adapter.DeliveryAdapter
import com.example.adminmunchmate.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding:ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }
        val customerName= arrayListOf(
            "Aman Shishodia",
            "Aman Saraswat",
            "Mike JohnSon"
        )
        val moneyStatus= arrayListOf(
            "recieved",
            "recieved",
            "not recieved"
        )
        val adapter=DeliveryAdapter(customerName,moneyStatus)
        binding.deliveryRecyclerView.adapter=adapter
        binding.deliveryRecyclerView.layoutManager=LinearLayoutManager(this)
    }
}