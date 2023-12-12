package com.example.adminmunchmate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.adminmunchmate.adapter.PendingOrderAdapter
import com.example.adminmunchmate.databinding.ActivityPendingOrderBinding

class PendingOrderActivity : AppCompatActivity() {
    private val binding:ActivityPendingOrderBinding by lazy{
        ActivityPendingOrderBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.backButton.setOnClickListener {
            finish()
        }
        val orderedcustomerName= arrayListOf(
            "Aman Shishodia",
            "Aman Saraswat",
            "Mike JohnSon"
        )
        val orderedQuantity= arrayListOf(
            "8",
            "5",
            "2"
        )
        val orderedFoodImage= arrayListOf(R.drawable.menu1,R.drawable.menu2,R.drawable.menu3)
        val adapter= PendingOrderAdapter(orderedcustomerName,orderedQuantity,orderedFoodImage,this)
        binding.pendingOrderRecyclerView.adapter=adapter
        binding.pendingOrderRecyclerView.layoutManager= LinearLayoutManager(this)
    }
}